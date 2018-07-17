package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.IntellexerClient;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.comparator.CompareResult;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Intellexer Comparator is a semantic solution specially developed to eliminate hassles of content comparison. With its
 * help it is possible to accurately compare documents and set the degree of similarity between them.
 * Intellexer Comparator analyzes each text document and converts it into a specific form that represents the essential
 * meaning of the document. In this new representation, concrete words, phrases and semantic relations between them,
 * initially used as the term base for the documents, acquire a generalized structure and meaning. As a result,
 * processing of information takes place at the level of the possible meanings of each word and at the level of the
 * ideas that each sentence and the context in general may express.
 */
public class Comparator extends BaseProcessor {
    private static final String COMPARE_TEXT_PATH = "/compareText";
    private static final String COMPARE_URLS_PATH = "/compareUrls";
    private static final String COMPARE_URL_AND_FILE_PATH = "/compareUrlwithFile";
    private static final String COMPARE_FILES_PATH = "/compareFiles";

    private static final String FILENAME_PARAM_NAME = "fileName";
    private static final String URL_PARAM_NAME = "url";
    private static final String FIRST_URL_PARAM_NAME = "url1";
    private static final String SECOND_URL_PARAM_NAME = "url2";
    private static final String FIRST_FILE_PARAM_NAME = "fileName1";
    private static final String SECOND_FILE_PARAM_NAME = "fileName2";
    private static final String FIRST_FILE_SIZE_PARAM_NAME = "firstFileSize";
    private static final String USE_CACHE_PARAM_NAME = "useCache";

    public Comparator(IntellexerClient client) {
        super(client);
    }

    /**
     * Compares contents of a specified texts.
     *
     * @param firstText  The first text to parse
     * @param secondText The second text to parse
     * @return {@link CompareResult} Comparison result
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>firstText</code> or <code>secondText</code>parameter is null or empty
     */
    public CompareResult compareTexts(String firstText, String secondText) throws IntellexerException {
        if (firstText == null || secondText == null || firstText.isEmpty() || secondText.isEmpty()) {
            throw new IllegalArgumentException("Texts may not be null or empty");
        }
        try {
            String requestBody = objectMapper.writeValueAsString(new Text(firstText, secondText));
            String result = client.getHttpManager().post(
                    buildEndpointURL(COMPARE_TEXT_PATH),
                    requestBody,

                    new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json")
            );
            return objectMapper.readValue(result, CompareResult.class);
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }

    /**
     * Compares contents of a specified URLs.
     *
     * @param firstURL  The first URL/Network path to the document to parse
     * @param secondURL The second URL/Network path to the document to parse
     * @param useCache  if TRUE, document content will be loaded from cache if there is any
     * @return Comparison result
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>firstURL</code> or <code>secondURL</code> parameter is null or empty
     */
    public CompareResult compareURLs(String firstURL, String secondURL, boolean useCache) throws IntellexerException {
        if (firstURL == null || secondURL == null || firstURL.isEmpty() || secondURL.isEmpty()) {
            throw new IllegalArgumentException("URLs may not be null or empty");
        }
        Map<String, String> params = new HashMap<>();
        params.put(FIRST_URL_PARAM_NAME, firstURL);
        params.put(SECOND_URL_PARAM_NAME, secondURL);
        params.put(USE_CACHE_PARAM_NAME, Boolean.toString(useCache));
        try {
            String result = client.getHttpManager().get(
                    buildEndpointURL(COMPARE_URLS_PATH),
                    params

            );
            return objectMapper.readValue(result, CompareResult.class);
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }

    /**
     * Compare content of a specified file with content of a specified URL
     *
     * @param url      The URL/Network path to the document to compare
     * @param file     File to parse
     * @param useCache if TRUE, document content will be loaded from cache if there is any
     * @return Comparison result
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>url</code> parameter is null or empty or <code>file</code> parameter is null
     */
    public CompareResult compareURLWithFile(String url, File file, boolean useCache) throws IntellexerException {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL may not be null or empty");
        }
        if (file == null) {
            throw new IllegalArgumentException("File may not be null");
        }
        Map<String, String> params = new HashMap<>();
        params.put(URL_PARAM_NAME, url);
        params.put(FILENAME_PARAM_NAME, file.getName());
        params.put(USE_CACHE_PARAM_NAME, Boolean.toString(useCache));
        try {
            String result = client.getHttpManager().post(
                    buildEndpointURL(COMPARE_URL_AND_FILE_PATH),
                    params,
                    file,
                    new BasicHeader(HttpHeaders.CONTENT_TYPE, "multipart/form-data")

            );
            return objectMapper.readValue(result, CompareResult.class);
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }

    /**
     * Compare content of two specified files
     *
     * @param firstFile  The first file to parse
     * @param secondFile The second file to parse
     * @return Comparison result
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>firstFile</code> or <code>secondFile</code> parameter  is null
     */
    public CompareResult compareFiles(File firstFile, File secondFile) throws IntellexerException {
        if (firstFile == null || secondFile == null) {
            throw new IllegalArgumentException("Files may not be null");
        }
        Map<String, String> params = new HashMap<>();
        params.put(FIRST_FILE_PARAM_NAME, firstFile.getName());
        params.put(SECOND_FILE_PARAM_NAME, secondFile.getName());
        params.put(FIRST_FILE_SIZE_PARAM_NAME, String.valueOf(firstFile.length()));
        try {
            String result = client.getHttpManager().post(
                    buildEndpointURL(COMPARE_FILES_PATH),
                    params,
                    Arrays.asList(firstFile, secondFile),
                    new BasicHeader(HttpHeaders.CONTENT_TYPE, "multipart/form-data")

            );
            return objectMapper.readValue(result, CompareResult.class);
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }

    private static class Text {
        private final String text1;
        private final String text2;

        Text(String text1, String text2) {
            this.text1 = text1;
            this.text2 = text2;
        }

        public String getText1() {
            return text1;
        }

        public String getText2() {
            return text2;
        }
    }
}
