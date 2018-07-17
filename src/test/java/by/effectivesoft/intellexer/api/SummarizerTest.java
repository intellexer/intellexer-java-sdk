package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseTest;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.DocumentStructure;
import by.effectivesoft.intellexer.model.param.summarizer.SummarizeFileParams;
import by.effectivesoft.intellexer.model.param.summarizer.SummarizeMultipleURLParams;
import by.effectivesoft.intellexer.model.param.summarizer.SummarizeTextParams;
import by.effectivesoft.intellexer.model.param.summarizer.SummarizeURLParams;
import by.effectivesoft.intellexer.model.summarizer.SummarizeMultipleDocumentResult;
import by.effectivesoft.intellexer.model.summarizer.SummarizeResult;
import by.effectivesoft.intellexer.util.IntellexerTestUtils;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SummarizerTest extends BaseTest {
    private static final String TEST_URL = "https://www.intellexer.com/about_us.html";
    private static final String TEST_TEXT = "Computer programming (often shortened to programming) is a process that " +
            "leads from an original formulation of a computing problem to executable computer programs.";

    private Summarizer summarizer;

    @Before
    public void setUp() throws Exception {
        summarizer = new Summarizer(client);
    }

    @Test
    public void summarizeURL_whenAllParamsAreSet_shouldSendGetRequestWithParamsAndReturnNotEmptyResult()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("summarizer/result.json"))
                .setResponseCode(200)
        );

        SummarizeURLParams params = new SummarizeURLParams.Builder()
                .setUseCache(true)
                .setFullTextTrees(true)
                .setLoadNamedEntityTree(true)
                .setWrapConcepts(true)
                .setLoadConceptsTree(true)
                .setUsePercentRestriction(true)
                .setTextStreamLength(1)
                .setReturnedTopicsCount(1)
                .setSummaryRestriction(1)
                .setConceptsRestriction(1)
                .setOptions(IntellexerTestUtils.buildOption())
                .setStructure(DocumentStructure.AUTODETECT)
                .build();

        SummarizeResult result = summarizer.summarizeURL(TEST_URL, params);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("GET", request.getMethod());
        assertEquals("/summarize", path.substring(0, path.indexOf('?')));
        assertEquals(14, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals(TEST_URL, requestParams.get("url"));
        assertEquals("true", requestParams.get("useCache"));
        assertEquals("true", requestParams.get("fullTextTrees"));
        assertEquals("true", requestParams.get("loadNamedEntityTree"));
        assertEquals("true", requestParams.get("wrapConcepts"));
        assertEquals("true", requestParams.get("loadConceptsTree"));
        assertEquals("true", requestParams.get("usePercentRestriction"));
        assertEquals("1", requestParams.get("textStreamLength"));
        assertEquals("1", requestParams.get("returnedTopicsCount"));
        assertEquals("1", requestParams.get("summaryRestriction"));
        assertEquals("1", requestParams.get("conceptsRestriction"));
        assertEquals(IntellexerTestUtils.readTestResponse("option.json"), requestParams.get("options"));
        assertEquals("Autodetect", requestParams.get("structure"));
        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getItems());
        assertTrue(result.getItems().isEmpty());
    }

    @Test
    public void summarizeURL_whenParamsAreNull_shouldSendGetRequestAndReturnNotEmptyResult()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("summarizer/result.json"))
                .setResponseCode(200)
        );

        SummarizeResult result = summarizer.summarizeURL(TEST_URL, null);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("GET", request.getMethod());
        assertEquals("/summarize", path.substring(0, path.indexOf('?')));
        assertEquals(2, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals(TEST_URL, requestParams.get("url"));
        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getItems());
        assertTrue(result.getItems().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void summarizeURL_whenURLIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        summarizer.summarizeURL(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void summarizeURL_whenURLIsEmpty_shouldThrowIllegalArgumentException() throws IntellexerException {
        summarizer.summarizeURL("", null);
    }

    @Test(expected = IntellexerException.class)
    public void summarizeURL_whenDeserializationFailed_shouldThrowIntellexerException() throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        summarizer.summarizeURL(TEST_URL, null);
    }

    @Test
    public void summarizeText_whenAllParamsAreSet_shouldSendPostRequestWithParamsAndReturnNotEmptyResult()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("summarizer/result.json"))
                .setResponseCode(200)
        );

        SummarizeTextParams params = new SummarizeTextParams.Builder()
                .setFullTextTrees(true)
                .setLoadNamedEntityTree(true)
                .setWrapConcepts(true)
                .setLoadConceptsTree(true)
                .setUsePercentRestriction(true)
                .setTextStreamLength(1)
                .setReturnedTopicsCount(1)
                .setSummaryRestriction(1)
                .setConceptsRestriction(1)
                .setOptions(IntellexerTestUtils.buildOption())
                .setStructure(DocumentStructure.AUTODETECT)
                .build();

        SummarizeResult result = summarizer.summarizeText(TEST_TEXT, params);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/summarizeText", path.substring(0, path.indexOf('?')));
        assertEquals(12, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals("true", requestParams.get("fullTextTrees"));
        assertEquals("true", requestParams.get("loadNamedEntityTree"));
        assertEquals("true", requestParams.get("wrapConcepts"));
        assertEquals("true", requestParams.get("loadConceptsTree"));
        assertEquals("true", requestParams.get("usePercentRestriction"));
        assertEquals("1", requestParams.get("textStreamLength"));
        assertEquals("1", requestParams.get("returnedTopicsCount"));
        assertEquals("1", requestParams.get("summaryRestriction"));
        assertEquals("1", requestParams.get("conceptsRestriction"));
        assertEquals(IntellexerTestUtils.readTestResponse("option.json"), requestParams.get("options"));
        assertEquals("Autodetect", requestParams.get("structure"));
        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getItems());
        assertTrue(result.getItems().isEmpty());
    }

    @Test
    public void summarizeText_whenParamsAreNull_shouldSendPostRequestAndReturnNotEmptyResult()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("summarizer/result.json"))
                .setResponseCode(200)
        );

        SummarizeResult result = summarizer.summarizeText(TEST_TEXT, null);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/summarizeText", path.substring(0, path.indexOf('?')));
        assertEquals(1, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getItems());
        assertTrue(result.getItems().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void summarizeText_whenTextIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        summarizer.summarizeText(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void summarizeText_whenTextIsEmpty_shouldThrowIllegalArgumentException() throws IntellexerException {
        summarizer.summarizeText("", null);
    }

    @Test(expected = IntellexerException.class)
    public void summarizeText_whenDeserializationFailed_shouldThrowIntellexerException() throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        summarizer.summarizeText(TEST_TEXT, null);
    }

    @Test
    public void summarizeFile_whenAllParamsAreSet_shouldSendPostRequestWithParamsAndReturnNotEmptyResult()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("summarizer/result.json"))
                .setResponseCode(200)
        );

        SummarizeFileParams params = new SummarizeFileParams.Builder()
                .setFullTextTrees(true)
                .setLoadNamedEntityTree(true)
                .setWrapConcepts(true)
                .setLoadConceptsTree(true)
                .setUsePercentRestriction(true)
                .setTextStreamLength(1)
                .setReturnedTopicsCount(1)
                .setSummaryRestriction(1)
                .setConceptsRestriction(1)
                .setOptions(IntellexerTestUtils.buildOption())
                .setStructure(DocumentStructure.AUTODETECT)
                .build();

        SummarizeResult result = summarizer.summarizeFile(IntellexerTestUtils.readTestFile("1.txt"), params);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/summarizeFileContent", path.substring(0, path.indexOf('?')));
        assertEquals(14, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals("1.txt", requestParams.get("fileName"));
        assertEquals("2055", requestParams.get("fileSize"));
        assertEquals("true", requestParams.get("fullTextTrees"));
        assertEquals("true", requestParams.get("loadNamedEntityTree"));
        assertEquals("true", requestParams.get("wrapConcepts"));
        assertEquals("true", requestParams.get("loadConceptsTree"));
        assertEquals("true", requestParams.get("usePercentRestriction"));
        assertEquals("1", requestParams.get("textStreamLength"));
        assertEquals("1", requestParams.get("returnedTopicsCount"));
        assertEquals("1", requestParams.get("summaryRestriction"));
        assertEquals("1", requestParams.get("conceptsRestriction"));
        assertEquals(IntellexerTestUtils.readTestResponse("option.json"), requestParams.get("options"));
        assertEquals("Autodetect", requestParams.get("structure"));
        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getItems());
        assertTrue(result.getItems().isEmpty());
    }

    @Test
    public void summarizeFile_whenParamsAreNull_shouldSendPostRequestAndReturnNotEmptyResult()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("summarizer/result.json"))
                .setResponseCode(200)
        );

        SummarizeResult result = summarizer.summarizeFile(IntellexerTestUtils.readTestFile("1.txt"), null);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/summarizeFileContent", path.substring(0, path.indexOf('?')));
        assertEquals(3, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals("1.txt", requestParams.get("fileName"));
        assertEquals("2055", requestParams.get("fileSize"));
        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getItems());
        assertTrue(result.getItems().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void summarizeFile_whenFileIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        summarizer.summarizeFile(null, null);
    }

    @Test(expected = IntellexerException.class)
    public void summarizeFile_whenDeserializationFailed_shouldThrowIntellexerException() throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        summarizer.summarizeFile(IntellexerTestUtils.readTestFile("2.txt"), null);
    }

    @Test
    public void summarizeMultipleURLs_whenAllParamsAreSet_shouldSendPostRequestWithParamsAndReturnNotEmptyResult()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("summarizer/multiple-url-result.json"))
                .setResponseCode(200)
        );

        SummarizeMultipleURLParams params = new SummarizeMultipleURLParams.Builder()
                .setFullTextTrees(true)
                .setLoadNamedEntityTree(true)
                .setLoadConceptsTree(true)
                .setUsePercentRestriction(true)
                .setReturnedTopicsCount(1)
                .setSummaryRestriction(1)
                .setConceptsRestriction(1)
                .setOptions(IntellexerTestUtils.buildOption())
                .setStructure(DocumentStructure.AUTODETECT)
                .setMaxRelatedFactsConcepts(1)
                .setMaxRelatedFactsSentences(1)
                .setRelatedFactsRequest("sea")
                .build();

        SummarizeMultipleDocumentResult result = summarizer.summarizeMultipleURLs(
                IntellexerTestUtils.buildListOfURLs(),
                params
        );

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/multiUrlSummary", path.substring(0, path.indexOf('?')));
        assertEquals(13, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals("true", requestParams.get("fullTextTrees"));
        assertEquals("true", requestParams.get("loadNamedEntityTree"));
        assertEquals("true", requestParams.get("loadConceptsTree"));
        assertEquals("true", requestParams.get("usePercentRestriction"));
        assertEquals("1", requestParams.get("returnedTopicsCount"));
        assertEquals("1", requestParams.get("summaryRestriction"));
        assertEquals("1", requestParams.get("conceptsRestriction"));
        assertEquals("1", requestParams.get("maxRelatedFactsConcepts"));
        assertEquals("1", requestParams.get("maxRelatedFactsSentences"));
        assertEquals("sea", requestParams.get("relatedFactsRequest"));
        assertEquals(IntellexerTestUtils.readTestResponse("option.json"), requestParams.get("options"));
        assertEquals("Autodetect", requestParams.get("structure"));
        assertNotNull(result);
        assertNotNull(result.getDocuments());
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getItems());
        assertTrue(result.getItems().isEmpty());
        assertTrue(result.getDocuments().size() == 2);
    }

    @Test
    public void summarizeMultipleURLs_whenParamsAreNull_shouldSendPostRequestAndReturnNotEmptyResult()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("summarizer/multiple-url-result.json"))
                .setResponseCode(200)
        );

        List<String> urls = Arrays.asList(
                "https://jlordiales.wordpress.com/2012/12/13/the-builder-pattern-in-practice/",
                "http://tutorials.jenkov.com/java-json/jackson-objectmapper.html"
        );
        SummarizeMultipleDocumentResult result = summarizer.summarizeMultipleURLs(urls, null);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/multiUrlSummary", path.substring(0, path.indexOf('?')));
        assertEquals(1, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertNotNull(result);
        assertNotNull(result.getDocuments());
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getItems());
        assertTrue(result.getItems().isEmpty());
        assertTrue(result.getDocuments().size() == 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void summarizeMultipleURLs_whenURLsAreNull_shouldThrowIllegalArgumentException()
            throws IntellexerException {
        summarizer.summarizeMultipleURLs(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void summarizeMultipleURLs_whenURLsContainNull_shouldThrowIllegalArgumentException()
            throws IntellexerException {
        summarizer.summarizeMultipleURLs(Collections.singletonList((String) null), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void summarizeMultipleURLs_whenURLsAreEmpty_shouldThrowIllegalArgumentException()
            throws IntellexerException {
        summarizer.summarizeMultipleURLs(Collections.<String>emptyList(), null);
    }

    @Test(expected = IntellexerException.class)
    public void summarizeMultipleURLs_whenDeserializationFailed_shouldThrowIntellexerException()
            throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        summarizer.summarizeMultipleURLs(IntellexerTestUtils.buildListOfURLs(), null);
    }
}