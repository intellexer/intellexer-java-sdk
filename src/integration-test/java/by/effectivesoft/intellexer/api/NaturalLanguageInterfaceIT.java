package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseIT;
import by.effectivesoft.intellexer.exception.IntellexerException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NaturalLanguageInterfaceIT extends BaseIT {
    private NaturalLanguageInterface languageInterface;

    @Before
    public void setUp() {
        languageInterface = new NaturalLanguageInterface(client);
    }

    @Test
    public void convertQueryToBool_whenValidText_shouldReturnNotEmptyResult() throws IntellexerException {
        String result = languageInterface.convertQueryToBool("How to increase an integration density?");
        assertNotNull(result);
        assertTrue(!result.isEmpty());
        assertEquals("\"increase OR increasing integration density\"", result);
    }
}