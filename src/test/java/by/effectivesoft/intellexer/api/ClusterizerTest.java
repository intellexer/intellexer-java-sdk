package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseTest;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.clusterizer.ClusterizeResult;
import by.effectivesoft.intellexer.model.param.clusterizer.ClusterizeFileParams;
import by.effectivesoft.intellexer.model.param.clusterizer.ClusterizeTextParams;
import by.effectivesoft.intellexer.model.param.clusterizer.ClusterizeURLParams;
import by.effectivesoft.intellexer.util.IntellexerTestUtils;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ClusterizerTest extends BaseTest {
    private static final String TEST_URL = "https://www.intellexer.com/about_us.html";
    private static final String TEST_TEXT = "Computer programming (often shortened to programming) is a process that " +
            "leads from an original formulation of a computing problem to executable computer programs.";

    private Clusterizer clusterizer;

    @Before
    public void setUp() throws Exception {
        clusterizer = new Clusterizer(client);
    }

    @Test
    public void clusterizeURL_whenAllParamsAreSet_shouldSendGetRequestWithParamsAndReturnNotEmptyResult()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("clusterizer/result.json"))
                .setResponseCode(200)
        );

        ClusterizeURLParams params = new ClusterizeURLParams.Builder()
                .setUseCache(true)
                .setLoadSentences(true)
                .setFullTextTrees(true)
                .setWrapConcepts(true)
                .setConceptsRestriction(5)
                .setOptions(IntellexerTestUtils.buildOption())
                .build();

        ClusterizeResult result = clusterizer.clusterizeURL(TEST_URL, params);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("GET", request.getMethod());
        assertEquals("/clusterize", path.substring(0, path.indexOf('?')));
        assertEquals(8, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals(TEST_URL, requestParams.get("url"));
        assertEquals("true", requestParams.get("fullTextTrees"));
        assertEquals("true", requestParams.get("loadSentences"));
        assertEquals("true", requestParams.get("wrapConcepts"));
        assertEquals("true", requestParams.get("useCache"));
        assertEquals("5", requestParams.get("conceptsRestriction"));
        assertEquals(IntellexerTestUtils.readTestResponse("option.json"), requestParams.get("options"));
        assertNotNull(result);
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getSentences());
        assertEquals(18, result.getSentences().size());
        assertEquals(5, result.getConceptTree().getChildren().size());
    }

    @Test
    public void clusterizeURL_whenParametersAreNull_shouldSendGetRequestAndReturnNotEmptyResult()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("clusterizer/result.json"))
                .setResponseCode(200)
        );

        ClusterizeResult result = clusterizer.clusterizeURL(TEST_URL, null);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("GET", request.getMethod());
        assertEquals("/clusterize", path.substring(0, path.indexOf('?')));
        assertEquals(2, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals(TEST_URL, requestParams.get("url"));
        assertNotNull(result);
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getSentences());
    }

    @Test(expected = IllegalArgumentException.class)
    public void clusterizeURL_whenURLIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        clusterizer.clusterizeURL(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void clusterizeURL_whenURLIsEmpty_shouldThrowIllegalArgumentException() throws IntellexerException {
        clusterizer.clusterizeURL("", null);
    }

    @Test(expected = IntellexerException.class)
    public void clusterizeURL_whenDeserializationFailed_shouldThrowIntellexerException() throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        clusterizer.clusterizeURL(TEST_URL, null);
    }

    @Test
    public void clusterizeText_whenAllParametersAreSet_shouldSendPostRequestReturnNotEmptyResultWithData()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("clusterizer/result.json"))
                .setResponseCode(200)
        );

        ClusterizeTextParams params = new ClusterizeTextParams.Builder()
                .setLoadSentences(true)
                .setFullTextTrees(true)
                .setWrapConcepts(true)
                .setTextStreamLength(1000)
                .build();
        ClusterizeResult result = clusterizer.clusterizeText(TEST_TEXT, params);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/clusterizeText", path.substring(0, path.indexOf('?')));
        assertEquals(5, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals("true", requestParams.get("loadSentences"));
        assertEquals("true", requestParams.get("fullTextTrees"));
        assertEquals("true", requestParams.get("wrapConcepts"));
        assertEquals("1000", requestParams.get("textStreamLength"));
        assertNotNull(result);
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getSentences());
        assertEquals(18, result.getSentences().size());
        assertEquals(5, result.getConceptTree().getChildren().size());
    }

    @Test
    public void clusterizeText_whenEmptyParams_shouldSendPostRequestAndReturnNotEmptyResult()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("clusterizer/result.json"))
                .setResponseCode(200)
        );

        ClusterizeResult result = clusterizer.clusterizeText(TEST_TEXT, null);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/clusterizeText", path.substring(0, path.indexOf('?')));
        assertEquals(1, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertNotNull(result);
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getSentences());
    }

    @Test(expected = IllegalArgumentException.class)
    public void clusterizeText_whenTextIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        clusterizer.clusterizeText(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void clusterizeText_whenTextIsEmpty_shouldThrowIllegalArgumentException() throws IntellexerException {
        clusterizer.clusterizeText("", null);
    }

    @Test(expected = IntellexerException.class)
    public void clusterizeText_whenDeserializationFailed_shouldThrowIntellexerException() throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        clusterizer.clusterizeText("test", null);
    }

    @Test
    public void clusterizeFile_whenAllParamsAreSet_shouldSendPostRequestAndReturnNotEmptyResul()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("clusterizer/result.json"))
                .setResponseCode(200)
        );

        ClusterizeFileParams params = new ClusterizeFileParams.Builder()
                .setLoadSentences(true)
                .setFullTextTrees(true)
                .setWrapConcepts(true)
                .setConceptsRestriction(5)
                .setOptions(IntellexerTestUtils.buildOption())
                .build();

        ClusterizeResult result = clusterizer.clusterizeFile(IntellexerTestUtils.readTestFile("1.txt"), params);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/clusterizeFileContent", path.substring(0, path.indexOf('?')));
        assertEquals(8, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals("true", requestParams.get("loadSentences"));
        assertEquals("true", requestParams.get("fullTextTrees"));
        assertEquals("true", requestParams.get("wrapConcepts"));
        assertEquals("5", requestParams.get("conceptsRestriction"));
        assertEquals(IntellexerTestUtils.readTestResponse("option.json"), requestParams.get("options"));
        assertEquals("1.txt", requestParams.get("fileName"));
        assertNotNull(result);
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getSentences());
        assertEquals(18, result.getSentences().size());
        assertEquals(5, result.getConceptTree().getChildren().size());
    }

    @Test
    public void clusterizeFile_whenAllParamsAreNull_shouldSendPostRequestAndReturnNotEmptyResul()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("clusterizer/result.json"))
                .setResponseCode(200)
        );

        ClusterizeResult result = clusterizer.clusterizeFile(IntellexerTestUtils.readTestFile("1.txt"), null);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/clusterizeFileContent", path.substring(0, path.indexOf('?')));
        assertEquals(3, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals("1.txt", requestParams.get("fileName"));
        assertNotNull(result);
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getSentences());
        assertEquals(18, result.getSentences().size());
        assertEquals(5, result.getConceptTree().getChildren().size());
    }


    @Test(expected = IllegalArgumentException.class)
    public void clusterizeFile_whenFileIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        clusterizer.clusterizeFile(null, null);
    }

    @Test(expected = IntellexerException.class)
    public void clusterizeFile_whenDeserializationFailed_shouldThrowIntellexerException() throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        clusterizer.clusterizeFile(IntellexerTestUtils.readTestFile("2.txt"), null);
    }


}