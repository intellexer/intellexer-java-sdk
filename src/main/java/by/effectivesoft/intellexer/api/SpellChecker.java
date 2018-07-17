package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.IntellexerClient;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.param.SpellCheckerParams;
import by.effectivesoft.intellexer.model.spelling.SpellCheckResult;

import java.io.IOException;

/**
 * Intellexer Spellchecker performs search and correction of spelling mistakes due to well-chosen linguistic rules,
 * including:
 * <ul>
 * <li>rules for context-dependent misspellings;</li>
 * <li>rules for evaluating the probability of possible corrections;</li>
 * <li>rules for evaluating spelling mistakes caused by different means of representing one sound with various letters and
 * letter combinations; dictionaries containing correct spelling variants.</li>
 * </ul>
 */
public class SpellChecker extends BaseProcessor {
    private static final String CHECK_TEXT_SPELLING_PATH = "/checkTextSpelling";

    public SpellChecker(IntellexerClient client) {
        super(client);
    }

    /**
     * Check Perform text spell check.
     *
     * @param text   Text to process
     * @param params Spell checker parameters
     * @return Spell checking result
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>text</code> parameter  is null or empty
     */
    public SpellCheckResult checkTextSpelling(String text, SpellCheckerParams params) throws IntellexerException {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text may not be null or empty");
        }
        try {
            String result = client.getHttpManager().post(
                    buildEndpointURL(CHECK_TEXT_SPELLING_PATH),
                    params,
                    text
            );
            return objectMapper.readValue(result, SpellCheckResult.class);
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }
}
