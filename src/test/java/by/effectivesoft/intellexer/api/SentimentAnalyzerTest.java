package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseTest;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.param.AnalyzeSentimentsParams;
import by.effectivesoft.intellexer.model.sentiment.AnalyzeSentimentsResult;
import by.effectivesoft.intellexer.model.sentiment.Review;
import by.effectivesoft.intellexer.util.IntellexerTestUtils;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.*;

public class SentimentAnalyzerTest extends BaseTest {
    private SentimentAnalyzer analyzer;

    @Before
    public void setUp() {
        analyzer = new SentimentAnalyzer(client);
    }


    @Test
    public void analyzeSentiments_whenValidReviewsAndParametersAreSet_shouldSendPostRequestAndReturnNotEmptyResultWithValidData()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("sentiment/result.json"))
                .setResponseCode(200)
        );

        AnalyzeSentimentsParams params = new AnalyzeSentimentsParams.Builder()
                .setOntology("hotels")
                .setLoadSentences(true)
                .build();

        AnalyzeSentimentsResult result = analyzer.analyzeSentiments(IntellexerTestUtils.buildListOfReviews(), params);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/analyzeSentiments", path.substring(0, path.indexOf('?')));
        assertEquals(3, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals("hotels", requestParams.get("ontology"));
        assertEquals("true", requestParams.get("loadSentences"));
        assertNotNull(result);
        assertNotNull(result.getSentences());
        assertNotNull(result.getSentiments());
        assertNull(result.getOntology());
        assertNull(result.getOpinions());
        assertTrue(result.getSentences().size() == 3);
        assertTrue(result.getSentiments().get(0).getId().equals("12"));
        assertTrue(result.getSentiments().get(1).getId().equals("45"));
    }

    @Test
    public void analyzeSentiments_whenValidReviewsAndParametersAreNull_shouldSendPostRequestAndReturnNotEmptyResultWithValidData()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("sentiment/result.json"))
                .setResponseCode(200)
        );

        AnalyzeSentimentsResult result = analyzer.analyzeSentiments(IntellexerTestUtils.buildListOfReviews(), null);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/analyzeSentiments", path.substring(0, path.indexOf('?')));
        assertEquals(1, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertNotNull(result);
        assertNotNull(result.getSentences());
        assertNotNull(result.getSentiments());
        assertNull(result.getOntology());
        assertNull(result.getOpinions());
        assertTrue(result.getSentences().size() == 3);
        assertTrue(result.getSentiments().get(0).getId().equals("12"));
        assertTrue(result.getSentiments().get(1).getId().equals("45"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void analyzeSentiments_whenReviewsAndParamsAreNull_shouldThrowIllegalArgumentException()
            throws IntellexerException {
        analyzer.analyzeSentiments(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void analyzeSentiments_whenReviewsContainNullAndParamsAreNull_shouldThrowIllegalArgumentException()
            throws IntellexerException {
        analyzer.analyzeSentiments(Collections.singletonList((Review) null), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void analyzeSentiments_whenReviewsAreEmptyAndParamsAreNull_shouldThrowIllegalArgumentException()
            throws IntellexerException {
        analyzer.analyzeSentiments(Collections.<Review>emptyList(), null);
    }

    @Test(expected = IntellexerException.class)
    public void analyzeSentiments_whenDeserializationFailed_shouldThrowIntellexerException() throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        analyzer.analyzeSentiments(IntellexerTestUtils.buildListOfReviews(), null);
    }

    @Test
    public void sentimentAnalyzerOntologies_whenMethodCalled_shouldSendGetRequestAndReturnListOfOntologies()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("sentiment/ontologies.json"))
                .setResponseCode(200)
        );

        List<String> ontologies = analyzer.sentimentAnalyzerOntologies();

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("GET", request.getMethod());
        assertEquals("/sentimentAnalyzerOntologies", path.substring(0, path.indexOf('?')));
        assertEquals(1, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertNotNull(ontologies);
        assertTrue(ontologies.size() == 3);
        assertTrue(ontologies.contains("Hotels"));
    }

    @Test(expected = IntellexerException.class)
    public void sentimentAnalyzerOntologies_whenDeserializationFailed_shouldThrowIntellexerException()
            throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        analyzer.sentimentAnalyzerOntologies();
    }

}