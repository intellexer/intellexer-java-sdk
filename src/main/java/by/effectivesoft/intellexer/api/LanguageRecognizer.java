package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.IntellexerClient;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.Language;

import java.io.IOException;
import java.util.List;

/**
 * Intellexer Language Recognizer identifies the language and character encoding of incoming documents. It supports more
 * than 30 languages, covering major European and Asian languages.
 */
public class LanguageRecognizer extends BaseProcessor {
    private static final String RECOGNIZE_LANGUAGE_PATH = "/recognizeLanguage";

    public LanguageRecognizer(IntellexerClient client) {
        super(client);
    }

    /**
     * Recognize language and encoding of an input text stream.
     *
     * @param text Text to process
     * @return List of detected languages
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>text</code> parameter is null or empty
     */
    public List<Language> recognizeLanguage(String text) throws IntellexerException {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text may not be null or empty");
        }
        try {
            String result = client.getHttpManager().post(
                    buildEndpointURL(RECOGNIZE_LANGUAGE_PATH),
                    text
            );
            return objectMapper.readValue(result, RecognizeLanguagesResult.class).getLanguages();
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }

    private static class RecognizeLanguagesResult {
        private List<Language> languages;

        public RecognizeLanguagesResult() {
            //required for Jackson JSON Processor
        }

        List<Language> getLanguages() {
            return languages;
        }

        void setLanguages(List<Language> languages) {
            this.languages = languages;
        }
    }
}
