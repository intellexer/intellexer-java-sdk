package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.IntellexerClient;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.param.summarizer.SummarizeFileParams;
import by.effectivesoft.intellexer.model.param.summarizer.SummarizeMultipleURLParams;
import by.effectivesoft.intellexer.model.param.summarizer.SummarizeTextParams;
import by.effectivesoft.intellexer.model.param.summarizer.SummarizeURLParams;
import by.effectivesoft.intellexer.model.summarizer.SummarizeMultipleDocumentResult;
import by.effectivesoft.intellexer.model.summarizer.SummarizeResult;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Intellexer Summarizer takes a document or set of documents as input and outputs a shorter document (summary),
 * which contains its most important content and main ideas.
 * Intellexer Summarizer’s unique feature is the possibility to create different kinds of summaries:
 * <ul>
 * <li>
 * <b>Theme-oriented:</b> the output summary includes the sentences, which are mostly relevant to a given topic
 * (e.g. politics, economics, sports and etc.);
 * </li>
 * <li>
 * <b>Structure-oriented:</b> the summary content depends on input document structure (e.g. scientific article,
 * patent, news article);
 * </li>
 * <li>
 * <b>Concept-oriented:</b> the importance of sentences is determined with respect to a number of user defined
 * concepts.
 * </li>
 * </ul>
 */
public class Summarizer extends BaseProcessor {
    private static final String SUMMARIZE_URL_PATH = "/summarize";
    private static final String SUMMARIZE_MULTIPLE_URLS_PATH = "/multiUrlSummary";
    private static final String SUMMARIZE_TEXT_PATH = "/summarizeText";
    private static final String SUMMARIZE_FILE_PATH = "/summarizeFileContent";

    private static final String FILE_NAME_PARAM_NAME = "fileName";
    private static final String FILE_SIZE_PARAM_NAME = "fileSize";
    private static final String URL_PARAM_NAME = "url";

    public Summarizer(IntellexerClient client) {
        super(client);
    }

    /**
     * Return summary data for a document from a given URL (http, file path).
     *
     * @param url    URL (http, file path) to process
     * @param params Summarizer URL parameters
     * @return Summary data for a document
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>url</code> parameter  is null or empty
     */
    public SummarizeResult summarizeURL(String url, SummarizeURLParams params) throws IntellexerException {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL may not be null or empty");
        }
        Map<String, String> requestParams = params != null ? params.toParamMap() : new HashMap<String, String>();
        requestParams.put(URL_PARAM_NAME, url);
        try {
            String result = client.getHttpManager().get(
                    buildEndpointURL(SUMMARIZE_URL_PATH),
                    requestParams
            );
            return objectMapper.readValue(result, SummarizeResult.class);
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }

    /**
     * Return summary data for a text
     *
     * @param text   Text to parse
     * @param params Summarizer text parameters
     * @return Summary data for a text
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>text</code> parameter is null or empty
     */
    public SummarizeResult summarizeText(String text, SummarizeTextParams params) throws IntellexerException {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text may not be null or empty");
        }
        try {
            String result = client.getHttpManager().post(
                    buildEndpointURL(SUMMARIZE_TEXT_PATH),
                    params,
                    text
            );
            return objectMapper.readValue(result, SummarizeResult.class);
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }

    /**
     * Return summary data for a file
     *
     * @param file   File to process
     * @param params Summarizer file parameters
     * @return Summary data for a file
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>file</code> parameter is null
     */
    public SummarizeResult summarizeFile(File file, SummarizeFileParams params) throws IntellexerException {
        if (file == null) {
            throw new IllegalArgumentException("File may not be null");
        }
        Map<String, String> requestParams = params != null ? params.toParamMap() : new HashMap<String, String>();
        requestParams.put(FILE_NAME_PARAM_NAME, file.getName());
        requestParams.put(FILE_SIZE_PARAM_NAME, String.valueOf(file.length()));
        try {
            String result = client.getHttpManager().post(
                    buildEndpointURL(SUMMARIZE_FILE_PATH),
                    requestParams,
                    file
            );
            return objectMapper.readValue(result, SummarizeResult.class);
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }

    /**
     * Generate summary for list of sources from a given URL/Network path
     *
     * @param urls   List of URLs/Network paths to process
     * @param params Summarizer multiple URL parameters
     * @return Summary data for list of sources
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>urls</code> parameter is null or contains null objects or empty
     */
    public SummarizeMultipleDocumentResult summarizeMultipleURLs(List<String> urls, SummarizeMultipleURLParams params)
            throws IntellexerException {
        if (urls == null || urls.contains(null) || urls.isEmpty()) {
            throw new IllegalArgumentException("List of urls may not be null or empty");
        }
        try {
            String result = client.getHttpManager().post(
                    buildEndpointURL(SUMMARIZE_MULTIPLE_URLS_PATH),
                    params,
                    objectMapper.writeValueAsString(urls),
                    new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json")
            );
            return objectMapper.readValue(result, SummarizeMultipleDocumentResult.class);
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }
}
