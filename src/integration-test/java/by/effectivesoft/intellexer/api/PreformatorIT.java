package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseIT;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.preformator.ParseResult;
import by.effectivesoft.intellexer.util.IntellexerTestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PreformatorIT extends BaseIT {
    private Preformator preformator;

    @Before
    public void setUp() {
        preformator = new Preformator(client);
    }

    @Test
    public void supportedDocumentStructures_whenMethodCalled_shouldReturnListOfStructures() throws IntellexerException {
        List<String> result = preformator.supportedDocumentStructures();

        assertNotNull(result);
        assertTrue(!result.isEmpty());
        assertTrue(result.contains("Patent"));
    }

    @Test
    public void supportedDocumentTopics_whenMethodCalled_shouldReturnListOfTopics() throws IntellexerException {
        List<String> result = preformator.supportedDocumentTopics();

        assertNotNull(result);
        assertTrue(!result.isEmpty());
        assertTrue(result.contains("Lifestyle.food"));
    }

    @Test
    public void parse_whenValidURL_shouldReturnNotEmptyResult() throws IntellexerException {
        String url = "https://www.intellexer.com/about_us.html";

        ParseResult result = preformator.parse(url, false);

        assertNotNull(result);
        assertNotNull(result.getTopics());
        assertEquals(result.getTopics().get(0), "Tech.information_technology");
        assertEquals(result.getLanguage(), "English");
    }

    @Test (expected = IntellexerException.class) // Temporary redirect
    public void parseFileContent_whenValidFile_shouldReturnNotEmptyResult() throws IntellexerException {
        ParseResult result = preformator.parseFile(IntellexerTestUtils.readTestFile("1.txt"));

        assertNotNull(result);
        assertNotNull(result.getTopics());
        assertEquals(result.getTopics().get(0), "Tech.information_technology");
        assertEquals(result.getLanguage(), "English");
    }

}