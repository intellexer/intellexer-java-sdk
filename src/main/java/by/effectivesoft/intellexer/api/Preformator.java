package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.IntellexerClient;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.preformator.ParseResult;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Intellexer Preformator extracts plain text and information about the text layout from documents of different formats
 * (doc, pdf, rtf, html, etc.). Also Preformator automatically determines the structure (patent, news or scientific
 * article, review, etc.) of document and its theme (economics, law, sports, etc.).
 */
public class Preformator extends BaseProcessor {
    private static final String SUPPORTED_DOCUMENT_STRUCTURES_PATH = "/supportedDocumentStructures";
    private static final String SUPPORTED_DOCUMENT_TOPICS_PATH = "/supportedDocumentTopics";
    private static final String PARSE_PATH = "/parse";
    private static final String PARSE_FILE_CONTENT_TOPICS_PATH = "/parseFile";

    private static final String FILENAME_PARAM_NAME = "fileName";
    private static final String URL_PARAM_NAME = "url";
    private static final String USE_CACHE_PARAM_NAME = "useCache";

    public Preformator(IntellexerClient client) {
        super(client);
    }

    /**
     * Return available Preformator Document structures.
     *
     * @return List of available Preformator Document structures.
     * @throws IntellexerException if API call or response deserialization failed
     */
    public List<String> supportedDocumentStructures() throws IntellexerException {
        try {
            String result = client.getHttpManager().get(buildEndpointURL(SUPPORTED_DOCUMENT_STRUCTURES_PATH));
            return objectMapper.readValue(result, new TypeReference<List<String>>() {
            });
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }

    /**
     * Return available Preformator Document topics.
     *
     * @return List of Preformator Document topics.
     * @throws IntellexerException if API call or response deserialization failed
     */
    public List<String> supportedDocumentTopics() throws IntellexerException {
        try {
            String result = client.getHttpManager().get(buildEndpointURL(SUPPORTED_DOCUMENT_TOPICS_PATH));
            return objectMapper.readValue(result, new TypeReference<List<String>>() {
            });
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }

    /**
     * Parse internet/intranet file content using Preformator
     *
     * @param url      URL to process
     * @param useCache if TRUE, content will be loaded from cache if there is any
     * @return Parsed content details
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>url</code> parameter is null or empty
     */
    public ParseResult parse(String url, boolean useCache) throws IntellexerException {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL may not be null or empty");
        }
        Map<String, String> params = new HashMap<>();
        params.put(URL_PARAM_NAME, url);
        params.put(USE_CACHE_PARAM_NAME, String.valueOf(useCache));
        try {
            String result = client.getHttpManager().get(
                    buildEndpointURL(PARSE_PATH),
                    params
            );
            return objectMapper.readValue(result, ParseResult.class);
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }

    /**
     * Parse file content using Preformator.
     *
     * @param file File to process
     * @return Parsed file details
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>file</code> parameter is null
     */
    public ParseResult parseFile(File file) throws IntellexerException {
        if (file == null) {
            throw new IllegalArgumentException("File may not be null");
        }
        try {
            String result = client.getHttpManager().post(
                    buildEndpointURL(PARSE_FILE_CONTENT_TOPICS_PATH),
                    Collections.singletonMap(FILENAME_PARAM_NAME, file.getName()),
                    file
            );
            return objectMapper.readValue(result, ParseResult.class);
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }
}
