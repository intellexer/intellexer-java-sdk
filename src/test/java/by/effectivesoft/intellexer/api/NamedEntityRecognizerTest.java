package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseTest;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.param.RecognizeParams;
import by.effectivesoft.intellexer.model.recognizer.NamedEntityRecognizerResult;
import by.effectivesoft.intellexer.util.IntellexerTestUtils;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NamedEntityRecognizerTest extends BaseTest {
    private static final String TEST_URL = "https://www.intellexer.com/about_us.html";
    private static final String TEST_TEXT = "Computer programming (often shortened to programming) is a process that " +
            "leads from an original formulation of a computing problem to executable computer programs.";

    private NamedEntityRecognizer recognizer;

    @Before
    public void setUp() {
        recognizer = new NamedEntityRecognizer(client);
    }

    @Test
    public void recognizeFromURL_whenValidURLAndNotEmptyParams_shouldSendGetRequestAndReturnNotEmptyData()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("entity-recognizer/result.json"))
                .setResponseCode(200)
        );

        RecognizeParams params = new RecognizeParams.Builder()
                .setLoadSentences(true)
                .setLoadNamedEntities(true)
                .setLoadRelationsTree(true)
                .build();
        NamedEntityRecognizerResult result = recognizer.recognizeFromURL(TEST_URL, params);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("GET", request.getMethod());
        assertEquals("/recognizeNe", path.substring(0, path.indexOf('?')));
        assertEquals(5, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals(TEST_URL, requestParams.get("url"));
        assertEquals("true", requestParams.get("loadNamedEntities"));
        assertEquals("true", requestParams.get("loadSentences"));
        assertEquals("true", requestParams.get("loadRelationsTree"));
        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNotNull(result.getSentences());
        assertNotNull(result.getRelationsTree());
        assertEquals("about_us.html.html", result.getDocument().getTitle());
    }

    @Test
    public void recognizeFromURL_whenValidURLAndEmptyParams_shouldSendGetRequestAndReturnNotEmptyData()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("entity-recognizer/result.json"))
                .setResponseCode(200)
        );
        NamedEntityRecognizerResult result = recognizer.recognizeFromURL(TEST_URL, null);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("GET", request.getMethod());
        assertEquals("/recognizeNe", path.substring(0, path.indexOf('?')));
        assertEquals(2, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals(TEST_URL, requestParams.get("url"));
        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNotNull(result.getSentences());
        assertNotNull(result.getRelationsTree());
        assertEquals("about_us.html.html", result.getDocument().getTitle());
    }


    @Test(expected = IllegalArgumentException.class)
    public void recognizeFromURL_whenURLIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        recognizer.recognizeFromURL(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void recognizeFromURL_whenURLIsEmpty_shouldThrowIllegalArgumentException() throws IntellexerException {
        recognizer.recognizeFromURL("", null);
    }

    @Test(expected = IntellexerException.class)
    public void recognizeFromURL_whenDeserializationFailed_shouldThrowIntellexerException() throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        recognizer.recognizeFromURL(TEST_URL, null);
    }

    @Test
    public void recognizeFromText_whenValidTextAndNotEmptyParams_shouldSendGetRequestAndReturnNotEmptyData()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("entity-recognizer/result.json"))
                .setResponseCode(200)
        );

        RecognizeParams params = new RecognizeParams.Builder()
                .setLoadSentences(true)
                .setLoadNamedEntities(true)
                .setLoadRelationsTree(true)
                .build();

        NamedEntityRecognizerResult result = recognizer.recognizeFromText(TEST_TEXT, params);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/recognizeNeText", path.substring(0, path.indexOf('?')));
        assertEquals(4, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals("true", requestParams.get("loadNamedEntities"));
        assertEquals("true", requestParams.get("loadSentences"));
        assertEquals("true", requestParams.get("loadRelationsTree"));
        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNotNull(result.getSentences());
        assertNotNull(result.getRelationsTree());
        assertEquals("about_us.html.html", result.getDocument().getTitle());
    }

    @Test
    public void recognizeFromText_whenValidTextAndEmptyParams_shouldSendGetRequestAndReturnNotEmptyData()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("entity-recognizer/result.json"))
                .setResponseCode(200)
        );

        NamedEntityRecognizerResult result = recognizer.recognizeFromText(TEST_TEXT, null);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/recognizeNeText", path.substring(0, path.indexOf('?')));
        assertEquals(1, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNotNull(result.getSentences());
        assertNotNull(result.getRelationsTree());
        assertEquals("about_us.html.html", result.getDocument().getTitle());
    }

    @Test(expected = IllegalArgumentException.class)
    public void recognizeFromText_whenTextIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        recognizer.recognizeFromText(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void recognizeFromText_whenTextIsEmpty_shouldThrowIllegalArgumentException() throws IntellexerException {
        recognizer.recognizeFromText("", null);
    }

    @Test(expected = IntellexerException.class)
    public void recognizeFromText_whenDeserializationFailed_shouldThrowIntellexerException() throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        recognizer.recognizeFromText(TEST_TEXT, null);
    }

    @Test
    public void recognizeFromFile_whenNotEmptyParams_shouldSendGetRequestAndReturnNotEmptyData()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("entity-recognizer/result.json"))
                .setResponseCode(200)
        );

        RecognizeParams params = new RecognizeParams.Builder()
                .setLoadSentences(true)
                .setLoadNamedEntities(true)
                .setLoadRelationsTree(true)
                .build();

        File file = IntellexerTestUtils.readTestFile("1.txt");
        NamedEntityRecognizerResult result = recognizer.recognizeFromFile(file, params);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/recognizeNeFileContent", path.substring(0, path.indexOf('?')));
        assertEquals(6, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals("true", requestParams.get("loadNamedEntities"));
        assertEquals("true", requestParams.get("loadSentences"));
        assertEquals("true", requestParams.get("loadRelationsTree"));
        assertEquals("1.txt", requestParams.get("fileName"));
        assertEquals("2055", requestParams.get("fileSize"));
        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNotNull(result.getSentences());
        assertNotNull(result.getRelationsTree());
        assertEquals("about_us.html.html", result.getDocument().getTitle());
    }

    @Test
    public void recognizeFromFile_whenEmptyParams_shouldSendGetRequestAndReturnNotEmptyData()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("entity-recognizer/result.json"))
                .setResponseCode(200)
        );

        File file = IntellexerTestUtils.readTestFile("1.txt");
        NamedEntityRecognizerResult result = recognizer.recognizeFromFile(file, null);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/recognizeNeFileContent", path.substring(0, path.indexOf('?')));
        assertEquals(3, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals("1.txt", requestParams.get("fileName"));
        assertEquals("2055", requestParams.get("fileSize"));
        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNotNull(result.getSentences());
        assertNotNull(result.getRelationsTree());
        assertEquals("about_us.html.html", result.getDocument().getTitle());
    }

    @Test(expected = IllegalArgumentException.class)
    public void recognizeFromFile_whenIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        recognizer.recognizeFromFile(null, null);
    }

    @Test(expected = IntellexerException.class)
    public void recognizeFromFile_whenDeserializationFailed_shouldThrowIntellexerException() throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        File file = IntellexerTestUtils.readTestFile("1.txt");

        recognizer.recognizeFromFile(file, null);
    }

}