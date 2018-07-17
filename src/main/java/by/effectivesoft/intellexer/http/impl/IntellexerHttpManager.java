package by.effectivesoft.intellexer.http.impl;

import by.effectivesoft.intellexer.exception.*;
import by.effectivesoft.intellexer.http.HttpManager;
import by.effectivesoft.intellexer.model.RestAPIError;
import by.effectivesoft.intellexer.model.param.BaseParams;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * HTTP Manager
 * Sends request to the server and gets response
 */
public class IntellexerHttpManager implements HttpManager {
    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final int CONNECTION_REQUEST_TIMEOUT = 5000;
    private static final int CONNECT_TIMEOUT = 10000;
    private static final int SOCKET_TIMEOUT = 10000;

    private static final String PARAMS_SEPARATOR = "&";
    private static final String PARAM_NAME_VALUE_SEPARATOR = "=";

    private HttpClientConnectionManager connectionManager;
    private ObjectMapper objectMapper;

    public IntellexerHttpManager() {
        this.connectionManager = new PoolingHttpClientConnectionManager();
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public String post(String endpointURL, Header... headers) throws IOException, HttpIntellexerException {
        return post(endpointURL, null, headers);
    }

    @Override
    public String post(String endpointURL, String entityBody, Header... headers)
            throws IOException, HttpIntellexerException {
        return post(endpointURL, (Map<String, String>) null, entityBody, headers);
    }

    @Override
    public String post(String endpointURL, BaseParams params, File entityBody, Header... headers)
            throws IOException, HttpIntellexerException {
        return post(endpointURL, params != null ? params.toParamMap() : null, entityBody, headers);
    }

    @Override
    public String post(String endpointURL, Map<String, String> params, File entityBody, Header... headers)
            throws IOException, HttpIntellexerException {
        HttpEntity body = entityBody != null ? new FileEntity(entityBody, ContentType.MULTIPART_FORM_DATA) : null;
        return post(endpointURL, params, body, headers);
    }

    @Override
    public String post(String endpointURL, BaseParams params, List<File> files, Header... headers)
            throws IOException, HttpIntellexerException {
        HttpEntity entityBody = buildMultiPartFileHttpEntity(files);
        return post(endpointURL, params != null ? params.toParamMap() : null, entityBody, headers);
    }

    @Override
    public String post(String endpointURL, Map<String, String> params, List<File> files, Header... headers)
            throws IOException, HttpIntellexerException {
        HttpEntity entityBody = buildMultiPartFileHttpEntity(files);
        return post(endpointURL, params, entityBody, headers);
    }

    @Override
    public String post(String endpointURL, BaseParams params, String entityBody, Header... headers)
            throws IOException, HttpIntellexerException {
        return post(endpointURL, params != null ? params.toParamMap() : null, entityBody, headers);
    }

    @Override
    public String post(String endpointURL, Map<String, String> params, String entityBody, Header... headers)
            throws IOException, HttpIntellexerException {
        HttpEntity body = entityBody != null ? new StringEntity(entityBody, Charset.forName(DEFAULT_CHARSET)) : null;
        return post(endpointURL, params, body, headers);
    }


    @Override
    public String post(String endpointURL,
                       Map<String, String> params,
                       HttpEntity entityBody,
                       Header... headers) throws IOException, HttpIntellexerException {

        String requestURL = buildRequestURL(endpointURL, params);

        HttpPost request = new HttpPost(requestURL);
        if (entityBody != null) {
            request.setEntity(entityBody);
        }
        for (Header header : headers) {
            request.addHeader(header);
        }
        return executeRequest(request);
    }

    @Override
    public String get(String endpointURL, Header... headers) throws IOException, HttpIntellexerException {
        return get(endpointURL, (Map<String, String>) null, headers);
    }

    @Override
    public String get(String endpointURL, BaseParams params, Header... headers)
            throws IOException, HttpIntellexerException {
        return get(endpointURL, params != null ? params.toParamMap() : null, headers);
    }

    @Override
    public String get(String endpointURL,
                      Map<String, String> params,
                      Header... headers) throws IOException, HttpIntellexerException {
        String requestURL = buildRequestURL(endpointURL, params);
        HttpGet request = new HttpGet(requestURL);
        for (Header header : headers) {
            request.addHeader(header);
        }
        return executeRequest(request);
    }

    private String executeRequest(HttpRequestBase request) throws IOException, HttpIntellexerException {
        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setCharset(Charset.forName(DEFAULT_CHARSET))
                .build();

        RequestConfig requestConfig = RequestConfig.custom()
                .setExpectContinueEnabled(true)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .setCookieSpec(CookieSpecs.DEFAULT)
                .build();

        try (CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultConnectionConfig(connectionConfig)
                .setDefaultRequestConfig(requestConfig)
                .build()) {
            CloseableHttpResponse httpResponse = httpClient.execute(request);
            return handleResponse(httpResponse);
        }
    }

    private String handleResponse(CloseableHttpResponse httpResponse) throws HttpIntellexerException {
        try {
            String responseBody = EntityUtils.toString(httpResponse.getEntity(), DEFAULT_CHARSET);
            if (httpResponse.getStatusLine().getStatusCode() >= HttpStatus.SC_MULTIPLE_CHOICES)
                handleStatusLine(httpResponse.getStatusLine(), responseBody);
            return responseBody;
        } catch (IOException e) {
            throw new HttpIntellexerException(e);
        }
    }

    private void handleStatusLine(StatusLine statusLine, String responseBody) throws HttpIntellexerException {
        int statusCode = statusLine.getStatusCode();
        String message;
        try {
            message = getErrorMessageFromBody(responseBody);
        } catch (IOException e) {
            message = null;
        }
        String errorMessage = message != null ? message : statusLine.getReasonPhrase();
        if (statusCode >= HttpStatus.SC_INTERNAL_SERVER_ERROR) {
            throw new ServerErrorIntellexerException(errorMessage);
        }
        if (statusCode == HttpStatus.SC_BAD_REQUEST) {
            throw new BadRequestIntellexerException(errorMessage);
        }
        if (statusCode == HttpStatus.SC_UNAUTHORIZED) {
            throw new UnauthorizedIntellexerException(errorMessage);
        }
        if (statusCode == HttpStatus.SC_FORBIDDEN) {
            throw new ForbiddenIntellexerException(errorMessage);
        }
        throw new HttpIntellexerException(errorMessage);
    }

    private String buildRequestURL(String url, Map<String, String> params) {
        StringBuilder builder = new StringBuilder(url);
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                builder
                        .append(PARAMS_SEPARATOR)
                        .append(param.getKey())
                        .append(PARAM_NAME_VALUE_SEPARATOR)
                        .append(param.getValue());
            }
        }
        return builder.toString();
    }

    private String getErrorMessageFromBody(String responseBody) throws IOException {
        return objectMapper.readValue(responseBody, RestAPIError.class).getMessage();
    }

    private HttpEntity buildMultiPartFileHttpEntity(List<File> files) {
        if (files == null || files.contains(null)) {
            return null;
        }
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        for (int i = 0; i < files.size(); i++) {
            String filename = "file" + (i + 1);
            builder.addPart(filename, new FileBody(files.get(i), ContentType.DEFAULT_BINARY));
        }
        return builder.build();
    }
}
