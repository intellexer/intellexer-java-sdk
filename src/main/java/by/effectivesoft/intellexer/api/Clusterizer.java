package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.IntellexerClient;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.clusterizer.ClusterizeResult;
import by.effectivesoft.intellexer.model.param.clusterizer.ClusterizeFileParams;
import by.effectivesoft.intellexer.model.param.clusterizer.ClusterizeTextParams;
import by.effectivesoft.intellexer.model.param.clusterizer.ClusterizeURLParams;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Intellexer Clusterizer enables effective organizing, normalizing, linking, and processing of documents, presenting of
 * retrieval results in a more logical, structured, and searchable way. Meanwhile, it can provide information seekers
 * with a means of browsing, and searching information efficiently in a user friendly format to fulfill the user’s
 * information need.
 * Intellexer Clusterizer extracts terms (concepts and noun phrases) from unstructured text information, detects
 * semantic relations (e.g., Verb-Subject-Object) between them, and finally forms clusters out of these terms,
 * differentiating between broader and narrower meanings terms with the help of custom Intellexer Linguistic Processor
 * and popular lexical database WordNet. Broader meaning terms indicate that the term represents a more inclusive
 * concept, usually corresponding to a superclass link in an inheritance hierarchy. Narrower meaning terms indicate
 * that the term represents a more specific concept. Particular consideration is given to the frequency of the term
 * occurrence in the analyzed text.
 */
public class Clusterizer extends BaseProcessor {
    private static final String CLUSTERIZE_PATH = "/clusterize";
    private static final String CLUSTERIZE_TEXT_PATH = "/clusterizeText";
    private static final String CLUSTERIZE_FILE_CONTENT_PATH = "/clusterizeFileContent";

    private static final String FILE_NAME_PARAM_NAME = "fileName";
    private static final String FILE_SIZE_PARAM_NAME = "fileSize";
    private static final String URL_PARAM_NAME = "url";

    public Clusterizer(IntellexerClient client) {
        super(client);
    }

    /**
     * Return tree of concepts for a document from a given URL (http, file)
     *
     * @param url    URL (http or file) to clusterize
     * @param params Clusterize URL parameters
     * @return A tree of concepts for a document
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>url</code> parameter is null or empty
     */
    public ClusterizeResult clusterizeURL(String url, ClusterizeURLParams params) throws IntellexerException {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL may not be null or empty");
        }
        Map<String, String> requestParams = params != null ? params.toParamMap() : new HashMap<String, String>();
        requestParams.put(URL_PARAM_NAME, url);
        try {
            String result = client.getHttpManager().get(
                    buildEndpointURL(CLUSTERIZE_PATH),
                    requestParams
            );
            return objectMapper.readValue(result, ClusterizeResult.class);
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }

    /**
     * Return tree of concepts for a text.
     *
     * @param text   Text to process
     * @param params Clusterize text parameters
     * @return A tree of concepts for a document
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>text</code> parameter is null or empty
     */
    public ClusterizeResult clusterizeText(String text, ClusterizeTextParams params) throws IntellexerException {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text may not be null or empty");
        }
        try {
            String result = client.getHttpManager().post(
                    buildEndpointURL(CLUSTERIZE_TEXT_PATH),
                    params,
                    text
            );
            return objectMapper.readValue(result, ClusterizeResult.class);
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }

    /**
     * Return tree of concepts for file content.
     *
     * @param file   File to process
     * @param params Clusterize file parameters
     * @return A tree of concepts for a document
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>file</code> parameter is null or empty
     */
    public ClusterizeResult clusterizeFile(File file, ClusterizeFileParams params) throws IntellexerException {
        if (file == null) {
            throw new IllegalArgumentException("File may not be null");
        }
        Map<String, String> requestParams = params != null ? params.toParamMap() : new HashMap<String, String>();
        requestParams.put(FILE_NAME_PARAM_NAME, file.getName());
        requestParams.put(FILE_SIZE_PARAM_NAME, String.valueOf(file.length()));
        try {
            String result = client.getHttpManager().post(
                    buildEndpointURL(CLUSTERIZE_FILE_CONTENT_PATH),
                    requestParams,
                    file
            );
            return objectMapper.readValue(result, ClusterizeResult.class);
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }
}
