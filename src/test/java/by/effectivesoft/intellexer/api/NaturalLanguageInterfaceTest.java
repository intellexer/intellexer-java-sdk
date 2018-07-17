package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseTest;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.util.IntellexerTestUtils;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;

public class NaturalLanguageInterfaceTest extends BaseTest {
    private NaturalLanguageInterface languageInterface;

    @Before
    public void setUp() {
        languageInterface = new NaturalLanguageInterface(client);
    }

    @Test
    public void convertQueryToBool_whenValidText_shouldSendPostRequestAndReturnNotEmptyResult()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody("\"increase OR increasing integration density\"")
                .setResponseCode(200)
        );
        String result = languageInterface.convertQueryToBool("How to increase an integration density?");

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/convertQueryToBool", path.substring(0, path.indexOf('?')));
        assertEquals(1, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertNotNull(result);
        assertTrue(!result.isEmpty());
        assertEquals("\"increase OR increasing integration density\"", result);
    }

    @Test
    public void convertQueryToBool_whenTextIsNull_shouldSendPostRequestAndReturnEmptyString()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody("\"\"")
                .setResponseCode(200)
        );
        String result = languageInterface.convertQueryToBool(null);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/convertQueryToBool", path.substring(0, path.indexOf('?')));
        assertEquals(1, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertNotNull(result);
        assertTrue(!result.isEmpty());
        assertEquals("\"\"", result);
    }

    @Test(expected = IntellexerException.class)
    public void convertQueryToBool_whenAPICallFailed_shouldThrowIntellexerException()
            throws IntellexerException, IOException {
        mockWebServer.shutdown();
        languageInterface.convertQueryToBool(null);
    }

}