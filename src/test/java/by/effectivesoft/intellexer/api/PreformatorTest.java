package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseTest;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.preformator.ParseResult;
import by.effectivesoft.intellexer.util.IntellexerTestUtils;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class PreformatorTest extends BaseTest {
    private Preformator preformator;

    @Before
    public void setUp() {
        preformator = new Preformator(client);
    }

    @Test
    public void supportedDocumentStructures_whenMethodCalled_shouldSendGetRequestAndReturnListOfStructures()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("preformator/document-structures.json"))
                .setResponseCode(200)
        );

        List<String> result = preformator.supportedDocumentStructures();

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("GET", request.getMethod());
        assertEquals("/supportedDocumentStructures", path.substring(0, path.indexOf('?')));
        assertEquals(1, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertNotNull(result);
        assertTrue(!result.isEmpty());
        assertTrue(result.contains("Patent"));
    }

    @Test(expected = IntellexerException.class)
    public void supportedDocumentStructures_whenDeserializationFailed_shouldThrowIntellexerException()
            throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        preformator.supportedDocumentStructures();
    }

    @Test
    public void supportedDocumentTopics_whenMethodCalled_shouldSendGetRequestAndReturnListOfTopics()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("preformator/document-topics.json"))
                .setResponseCode(200)
        );

        List<String> result = preformator.supportedDocumentTopics();

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("GET", request.getMethod());
        assertEquals("/supportedDocumentTopics", path.substring(0, path.indexOf('?')));
        assertEquals(1, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertNotNull(result);
        assertTrue(!result.isEmpty());
        assertTrue(result.contains("Lifestyle.food"));
    }

    @Test(expected = IntellexerException.class)
    public void supportedDocumentTopics_whenDeserializationFailed_shouldThrowIntellexerException()
            throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        preformator.supportedDocumentTopics();
    }

    @Test
    public void parse_whenValidURL_shouldSendGetRequestAndReturnNotEmptyResult() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("preformator/result.json"))
                .setResponseCode(200)
        );

        String url = "https://www.intellexer.com/about_us.html";
        ParseResult result = preformator.parse(url, false);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("GET", request.getMethod());
        assertEquals("/parse", path.substring(0, path.indexOf('?')));
        assertEquals(3, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals(url, requestParams.get("url"));
        assertEquals("false", requestParams.get("useCache"));
        assertNotNull(result);
        assertNotNull(result.getTopics());
        assertEquals(result.getTopics().get(0), "Tech.information_technology");
        assertEquals(result.getLanguage(), "English");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_whenURLIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        preformator.parse(null, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_whenURLIsEmpty_shouldThrowIllegalArgumentException() throws IntellexerException {
        preformator.parse("", false);
    }

    @Test(expected = IntellexerException.class)
    public void parse_whenDeserializationFailed_shouldThrowIntellexerException() throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        preformator.parse("https://www.intellexer.com/about_us.html", false);
    }

    @Test
    public void parseFileContent_whenValidFile_shouldSendPostRequestAndReturnNotEmptyResult() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("preformator/result.json"))
                .setResponseCode(200)
        );

        ParseResult result = preformator.parseFile(IntellexerTestUtils.readTestFile("1.txt"));

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/parseFile", path.substring(0, path.indexOf('?')));
        assertEquals(2, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals("1.txt", requestParams.get("fileName"));
        assertNotNull(result);
        assertNotNull(result.getTopics());
        assertEquals(result.getTopics().get(0), "Tech.information_technology");
        assertEquals(result.getLanguage(), "English");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseFile_whenFileIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        preformator.parseFile(null);
    }

    @Test(expected = IntellexerException.class)
    public void parseFile_whenDeserializationFailed_shouldThrowIntellexerException() throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        preformator.parseFile(IntellexerTestUtils.readTestFile("1.txt"));
    }

}