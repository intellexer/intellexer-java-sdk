package by.effectivesoft.intellexer.http;

import by.effectivesoft.intellexer.exception.HttpIntellexerException;
import by.effectivesoft.intellexer.model.param.BaseParams;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface HttpManager {
    /**
     * Execute POST request
     *
     * @param endpointURL Request URL
     * @param headers     Headers to be added to the request
     * @return Response from the server (JSON as String)
     * @throws IOException             if API call failed
     * @throws HttpIntellexerException if server responses with 4XX or 5XX status code
     */
    String post(String endpointURL, Header... headers)
            throws IOException, HttpIntellexerException;

    /**
     * Execute POST request
     *
     * @param endpointURL Request URL
     * @param entityBody  Request body
     * @param headers     Headers to be added to the request
     * @return Response from the server (JSON as String)
     * @throws IOException             if API call failed
     * @throws HttpIntellexerException if server responses with 4XX or 5XX status code
     */
    String post(String endpointURL, String entityBody, Header... headers)
            throws IOException, HttpIntellexerException;

    /**
     * Execute POST request
     *
     * @param endpointURL Request URL
     * @param params      Parameters to be added to the request
     * @param entityBody  Request body
     * @param headers     Headers to be added to the request
     * @return Response from the server (JSON as String)
     * @throws IOException             if API call failed
     * @throws HttpIntellexerException if server responses with 4XX or 5XX status code
     */
    String post(String endpointURL, BaseParams params, File entityBody, Header... headers)
            throws IOException, HttpIntellexerException;

    /**
     * Execute POST request
     *
     * @param endpointURL Request URL
     * @param params      Parameters to be added to the request
     * @param entityBody  Request body
     * @param headers     Headers to be added to the request
     * @return Response from the server (JSON as String)
     * @throws IOException             if API call failed
     * @throws HttpIntellexerException if server responses with 4XX or 5XX status code
     */
    String post(String endpointURL, Map<String, String> params, File entityBody, Header... headers)
            throws IOException, HttpIntellexerException;

    /**
     * Execute POST request
     *
     * @param endpointURL Request URL
     * @param params      Parameters to be added to the request
     * @param files       Request body as multiple files
     * @param headers     Headers to be added to the request
     * @return Response from the server (JSON as String)
     * @throws IOException             if API call failed
     * @throws HttpIntellexerException if server responses with 4XX or 5XX status code
     */
    String post(String endpointURL, BaseParams params, List<File> files, Header... headers)
            throws IOException, HttpIntellexerException;

    /**
     * Execute POST request
     *
     * @param endpointURL Request URL
     * @param params      Parameters to be added to the request
     * @param files       Request body as multiple files
     * @param headers     Headers to be added to the request
     * @return Response from the server (JSON as String)
     * @throws IOException             if API call failed
     * @throws HttpIntellexerException if server responses with 4XX or 5XX status code
     */
    String post(String endpointURL, Map<String, String> params, List<File> files, Header... headers)
            throws IOException, HttpIntellexerException;

    /**
     * Execute POST request
     *
     * @param endpointURL Request URL
     * @param params      Parameters to be added to the request
     * @param entityBody  Request body
     * @param headers     Headers to be added to the request
     * @return Response from the server (JSON as String)
     * @throws IOException             if API call failed
     * @throws HttpIntellexerException if server responses with 4XX or 5XX status code
     */
    String post(String endpointURL, BaseParams params, String entityBody, Header... headers)
            throws IOException, HttpIntellexerException;

    /**
     * Execute POST request
     *
     * @param endpointURL Request URL
     * @param params      Parameters to be added to the request
     * @param entityBody  Request body
     * @param headers     Headers to be added to the request
     * @return Response from the server (JSON as String)
     * @throws IOException             if API call failed
     * @throws HttpIntellexerException if server responses with 4XX or 5XX status code
     */
    String post(String endpointURL, Map<String, String> params, String entityBody, Header... headers)
            throws IOException, HttpIntellexerException;


    /**
     * Execute POST request
     *
     * @param endpointURL Request URL
     * @param params      Parameters to be added to the request
     * @param entityBody  Request body
     * @param headers     Headers to be added to the request
     * @return Response from the server (JSON as String)
     * @throws IOException             if API call failed
     * @throws HttpIntellexerException if server responses with 4XX or 5XX status code
     */
    String post(String endpointURL,
                        Map<String, String> params,
                        HttpEntity entityBody,
                        Header... headers) throws IOException, HttpIntellexerException;
    /**
     * Execute GET request
     *
     * @param endpointURL Request URL
     * @param headers     Headers to be added to the request
     * @return Response from the server (JSON as String)
     * @throws IOException             if API call failed
     * @throws HttpIntellexerException if server responses with 4XX or 5XX status code
     */
    String get(String endpointURL, Header... headers)
            throws IOException, HttpIntellexerException;

    /**
     * Execute GET request
     *
     * @param endpointURL Request URL
     * @param params      Parameters to be added to the request
     * @param headers     Headers to be added to the request
     * @return Response from the server (JSON as String)
     * @throws IOException             if API call failed
     * @throws HttpIntellexerException if server responses with 4XX or 5XX status code
     */
    String get(String endpointURL, BaseParams params, Header... headers)
            throws IOException, HttpIntellexerException;

    /**
     * Execute GET request
     *
     * @param endpointURL Request URL
     * @param params      Parameters to be added to the request
     * @param headers     Headers to be added to the request
     * @return Response from the server (JSON as String)
     * @throws IOException             if API call failed
     * @throws HttpIntellexerException if server responses with 4XX or 5XX status code
     */
    String get(String endpointURL, Map<String, String> params, Header... headers)
            throws IOException, HttpIntellexerException;

}
