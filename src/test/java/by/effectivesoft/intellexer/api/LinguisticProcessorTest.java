package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseTest;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.linguistic.Sentence;
import by.effectivesoft.intellexer.model.param.AnalyzeTextParams;
import by.effectivesoft.intellexer.util.IntellexerTestUtils;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class LinguisticProcessorTest extends BaseTest {
    private static final String TEST_TEXT = "Intellexer Summarizer has an unique feature";


    private LinguisticProcessor processor;

    @Before
    public void setUp() {
        processor = new LinguisticProcessor(client);
    }

    @Test
    public void analyzeText_whenValidTextAndNotEmptyParams_shouldSendPostRequestReturnNotEmptyListOfSentencesWithData()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("processor/result.json"))
                .setResponseCode(200)
        );

        AnalyzeTextParams params = new AnalyzeTextParams.Builder()
                .setLoadRelations(true)
                .setLoadTokens(true)
                .setLoadSentences(true)
                .build();

        List<Sentence> sentences = processor.analyzeText(TEST_TEXT, params);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/analyzeText", path.substring(0, path.indexOf('?')));
        assertEquals(4, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals("true", requestParams.get("loadSentences"));
        assertEquals("true", requestParams.get("loadTokens"));
        assertEquals("true", requestParams.get("loadRelations"));
        assertNotNull(sentences);
        assertTrue(sentences.size() == 1);
        assertNotNull(sentences.get(0).getText());
        assertNotNull(sentences.get(0).getTokens());
        assertNotNull(sentences.get(0).getRelations());
        assertEquals(TEST_TEXT, sentences.get(0).getText().getContent());
    }

    @Test
    public void analyzeText_whenValidTextAndParamsAreNull_shouldSendPostRequestReturnNotEmptyListOfSentencesWithData()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("processor/result.json"))
                .setResponseCode(200)
        );

        List<Sentence> sentences = processor.analyzeText(TEST_TEXT, null);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/analyzeText", path.substring(0, path.indexOf('?')));
        assertEquals(1, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertNotNull(sentences);
        assertTrue(sentences.size() == 1);
        assertNotNull(sentences.get(0).getText());
        assertNotNull(sentences.get(0).getTokens());
        assertNotNull(sentences.get(0).getRelations());
        assertEquals(TEST_TEXT, sentences.get(0).getText().getContent());
    }


    @Test(expected = IllegalArgumentException.class)
    public void analyzeText_whenTextIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        processor.analyzeText(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void analyzeText_whenTextIsEmpty_shouldThrowIllegalArgumentException() throws IntellexerException {
        processor.analyzeText("", null);
    }

    @Test(expected = IntellexerException.class)
    public void analyzeText_whenDeserializationFailed_shouldThrowIntellexerException() throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        processor.analyzeText("text", null);
    }

}