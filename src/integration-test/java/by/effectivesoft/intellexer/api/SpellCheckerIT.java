package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseIT;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.exception.ServerErrorIntellexerException;
import by.effectivesoft.intellexer.model.param.SpellCheckerParams;
import by.effectivesoft.intellexer.model.spelling.SpellCheckResult;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SpellCheckerIT extends BaseIT {
    private SpellChecker spellChecker;

    @Before
    public void setUp() throws Exception {
        spellChecker = new SpellChecker(client);
    }

    @Test
    public void checkTextSpelling_whenValidTextAndNotEmptyParams_shouldReturnNotEmptyResult() throws IntellexerException {
        String text = "The goor thing about this way of bulilding objects of the classs is that it works. " +
                "However, the problem with this approach should be pretty obvious.";

        SpellCheckerParams params = new SpellCheckerParams.Builder()
                .setLanguage("ENGLISH")
                .setMinProbabilityWeight(30)
                .setErrorBound(3)
                .build();

        SpellCheckResult result = spellChecker.checkTextSpelling(text, params);

        assertNotNull(result);
        assertNotNull(result.getCorrections());
        assertNotNull(result.getSourceSentences());
        assertNotNull(result.getProcessedSentences());
        assertEquals(result.getProcessedSentences().size(), result.getSourceSentences().size());
    }

    @Test
    public void checkTextSpelling_whenValidTextAndParamsAreNull_shouldReturnNotEmptyResult() throws IntellexerException {
        String text = "The goor thing about this way of bulilding objects of the classs is that it works. " +
                "However, the problem with this approach should be pretty obvious.";

        SpellCheckResult result = spellChecker.checkTextSpelling(text, null);

        assertNotNull(result);
        assertNotNull(result.getCorrections());
        assertNotNull(result.getSourceSentences());
        assertNotNull(result.getProcessedSentences());
        assertEquals(result.getProcessedSentences().size(), result.getSourceSentences().size());
    }

    @Test(expected = ServerErrorIntellexerException.class)
    public void checkTextSpelling_whenValidTextAndNotSupportedLanguage_shouldThrowServerErrorIntellexerException()
            throws IntellexerException {
        String text = "The goor thing about this way of bulilding is ...";

        SpellCheckerParams params = new SpellCheckerParams.Builder()
                .setLanguage("French")
                .build();

        spellChecker.checkTextSpelling(text, params);
    }

}