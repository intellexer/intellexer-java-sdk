package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseIT;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.param.RecognizeParams;
import by.effectivesoft.intellexer.model.recognizer.NamedEntityRecognizerResult;
import by.effectivesoft.intellexer.util.IntellexerTestUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class NamedEntityRecognizerIT extends BaseIT {
    private NamedEntityRecognizer recognizer;

    @Before
    public void setUp() throws Exception {
        recognizer = new NamedEntityRecognizer(client);
    }

    @Test
    public void recognizeFromURLwhenValidURLAndNotEmptyParams_shouldReturnNotEmptyData() throws IntellexerException {
        String url = "https://www.intellexer.com/about_us.html";

        RecognizeParams params = new RecognizeParams.Builder()
                .setLoadSentences(true)
                .setLoadNamedEntities(true)
                .setLoadRelationsTree(true)
                .build();
        NamedEntityRecognizerResult result = recognizer.recognizeFromURL(url, params);

        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNotNull(result.getSentences());
        assertNotNull(result.getRelationsTree());
        assertEquals("about_us.html.html", result.getDocument().getTitle());
    }

    @Test
    public void recognizeFromURL_whenParamsAreNull_shouldReturnNullSentencesAndTree() throws IntellexerException {
        String url = "https://www.intellexer.com/about_us.html";

        NamedEntityRecognizerResult result = recognizer.recognizeFromURL(url, null);

        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNull(result.getSentences());
        assertNull(result.getRelationsTree());
        assertEquals("about_us.html.html", result.getDocument().getTitle());
    }


    @Test
    public void recognizeFromText_whenValidTextAndNotEmptyParams_shouldReturnNotEmptyData() throws IntellexerException {
        String text = "Computer programming (often shortened to programming) is a process that leads from an " +
                "original formulation of a computing problem to executable computer programs.";

        RecognizeParams params = new RecognizeParams.Builder()
                .setLoadSentences(true)
                .setLoadNamedEntities(true)
                .setLoadRelationsTree(true)
                .build();

        NamedEntityRecognizerResult result = recognizer.recognizeFromText(text, params);

        assertNotNull(result);
        assertNull(result.getDocument());
        assertNotNull(result.getSentences());
        assertTrue(result.getSentences().size() == 1);
        assertNotNull(result.getRelationsTree());
    }

    @Test
    public void recognizeFromText_whenValidTextAndEmptyParams_shouldReturnNullSentencesAndTreeAndEmptyEntities()
            throws IntellexerException {
        String text = "Computer programming (often shortened to programming) is a process that leads from an " +
                "original formulation of a computing problem to executable computer programs.";

        NamedEntityRecognizerResult result = recognizer.recognizeFromText(text, null);

        assertNotNull(result);
        assertNull(result.getDocument());
        assertNull(result.getSentences());
        assertNull(result.getRelationsTree());
        assertNotNull(result.getEntities());
        assertTrue(result.getEntities().isEmpty());
    }

    @Test
    public void recognizeFromFile_whenNotEmptyParams_shouldReturnNotEmptyData() throws IntellexerException {
        File file = IntellexerTestUtils.readTestFile("1.txt");

        RecognizeParams params = new RecognizeParams.Builder()
                .setLoadSentences(true)
                .setLoadNamedEntities(true)
                .setLoadRelationsTree(true)
                .build();

        NamedEntityRecognizerResult result = recognizer.recognizeFromFile(file, params);
        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNotNull(result.getSentences());
        assertNotNull(result.getEntities());
        assertNotNull(result.getRelationsTree());
        assertEquals("1.txt", result.getDocument().getTitle());
    }

    @Test
    public void recognizeFromFile_whenNotEmptyParams_shouldReturnNullSentencesAndTreeAndNotEmptyEntities()
            throws IntellexerException {
        File file = IntellexerTestUtils.readTestFile("1.txt");

        NamedEntityRecognizerResult result = recognizer.recognizeFromFile(file, null);

        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNull(result.getSentences());
        assertNull(result.getRelationsTree());
        assertNotNull(result.getEntities());
        assertTrue(result.getEntities().size() == 2);
        assertEquals("1.txt", result.getDocument().getTitle());
    }

}