package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.IntellexerClient;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.param.RecognizeParams;
import by.effectivesoft.intellexer.model.recognizer.NamedEntityRecognizerResult;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Intellexer Named Entity Recognizer successfully identifies not only personal names, names of organizations and
 * geographical locations, but also extracts such entities as positions/occupations, nationalities, dates, ages,
 * durations and names of events.
 */
public class NamedEntityRecognizer extends BaseProcessor {
    private static final String RECOGNIZE_PATH = "/recognizeNe";
    private static final String RECOGNIZE_TEXT_PATH = "/recognizeNeText";
    private static final String RECOGNIZE_FILE_CONTENT_PATH = "/recognizeNeFileContent";

    private static final String FILE_NAME_PARAM_NAME = "fileName";
    private static final String FILE_SIZE_PARAM_NAME = "fileSize";
    private static final String URL_PARAM_NAME = "url";

    public NamedEntityRecognizer(IntellexerClient client) {
        super(client);
    }

    /**
     * Load Named Entities from a document from a given URL (http, file path).
     *
     * @param url    URL (http, file path) to process
     * @param params Recognizer parameters
     * @return Named Entities from a document
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>url</code> parameter is null or empty
     */
    public NamedEntityRecognizerResult recognizeFromURL(String url, RecognizeParams params) throws IntellexerException {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL may not be null or empty");
        }
        Map<String, String> requestParams = params != null ? params.toParamMap() : new HashMap<String, String>();
        requestParams.put(URL_PARAM_NAME, url);
        try {
            String result = client.getHttpManager().get(
                    buildEndpointURL(RECOGNIZE_PATH),
                    requestParams
            );
            return objectMapper.readValue(result, NamedEntityRecognizerResult.class);
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }

    /**
     * Load Named Entities from a text.
     *
     * @param text   Text to process
     * @param params Recognizer parameters
     * @return Named Entities from a text
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>text</code> parameter is null or empty
     */
    public NamedEntityRecognizerResult recognizeFromText(String text, RecognizeParams params)
            throws IntellexerException {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text may not be null or empty");
        }
        try {
            String result = client.getHttpManager().post(
                    buildEndpointURL(RECOGNIZE_TEXT_PATH),
                    params,
                    text
            );
            return objectMapper.readValue(result, NamedEntityRecognizerResult.class);
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }

    /**
     * Load Named Entities from a file.
     *
     * @param file   File to process
     * @param params Recognizer parameters
     * @return Named Entities from a file
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>file</code> parameter is null
     */
    public NamedEntityRecognizerResult recognizeFromFile(File file, RecognizeParams params)
            throws IntellexerException {
        if (file == null) {
            throw new IllegalArgumentException("File may not be null");
        }
        Map<String, String> requestParams = params != null ? params.toParamMap() : new HashMap<String, String>();
        requestParams.put(FILE_NAME_PARAM_NAME, file.getName());
        requestParams.put(FILE_SIZE_PARAM_NAME, String.valueOf(file.length()));
        try {
            String result = client.getHttpManager().post(
                    buildEndpointURL(RECOGNIZE_FILE_CONTENT_PATH),
                    requestParams,
                    file
            );
            return objectMapper.readValue(result, NamedEntityRecognizerResult.class);
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }
}
