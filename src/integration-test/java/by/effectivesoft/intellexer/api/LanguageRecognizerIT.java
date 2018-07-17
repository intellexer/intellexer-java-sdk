package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseIT;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.Language;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LanguageRecognizerIT extends BaseIT {
    private LanguageRecognizer languageRecognizer;

    @Before
    public void setUp() throws Exception {
        languageRecognizer = new LanguageRecognizer(client);
    }

    @Test
    public void recognizeLanguage_whenValidText_shouldReturnNotEmptyData() throws IntellexerException {
        String text = "The good thing about this way of building objects of the class is that it works.";
        List<Language> languageList = languageRecognizer.recognizeLanguage(text);
        assertNotNull(languageList);
        assertTrue(!languageList.isEmpty());
        assertEquals("English", languageList.get(0).getLanguageName());
    }
}