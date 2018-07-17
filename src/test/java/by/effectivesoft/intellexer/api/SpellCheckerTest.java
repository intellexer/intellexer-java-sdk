package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseTest;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.param.SpellCheckerParams;
import by.effectivesoft.intellexer.model.spelling.SpellCheckResult;
import by.effectivesoft.intellexer.util.IntellexerTestUtils;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SpellCheckerTest extends BaseTest {
    private SpellChecker spellChecker;

    @Before
    public void setUp() throws Exception {
        spellChecker = new SpellChecker(client);
    }

    @Test
    public void checkTextSpelling_whenValidTextAndNotEmptyParams_shouldSendPostRequestAndReturnNotEmptyResult()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("spell-checker/result.json"))
                .setResponseCode(200)
        );

        String text = "The goor thing about this way of bulilding objects of the classs is that it works. " +
                "However, the problem with this approach should be pretty obvious.";

        SpellCheckerParams params = new SpellCheckerParams.Builder()
                .setErrorBound(1)
                .setErrorTune(1)
                .setLanguage("english")
                .setMinProbabilityTune(1)
                .setMinProbabilityWeight(1)
                .setSeparateLines(true)
                .build();

        SpellCheckResult result = spellChecker.checkTextSpelling(text, params);
        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/checkTextSpelling", path.substring(0, path.indexOf('?')));
        assertEquals(7, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals("true", requestParams.get("separateLines"));
        assertEquals("english", requestParams.get("language"));
        assertEquals("1", requestParams.get("errorTune"));
        assertEquals("1", requestParams.get("errorBound"));
        assertEquals("1", requestParams.get("minProbabilityTune"));
        assertEquals("1", requestParams.get("minProbabilityWeight"));
        assertNotNull(result);
        assertNotNull(result.getCorrections());
        assertNotNull(result.getSourceSentences());
        assertNotNull(result.getProcessedSentences());
        assertEquals(result.getProcessedSentences().size(), result.getSourceSentences().size());
    }


    @Test
    public void checkTextSpelling_whenValidTextEmptyParams_shouldSendPostRequestAndReturnNotEmptyResult()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("spell-checker/result.json"))
                .setResponseCode(200)
        );

        String text = "The goor thing about this way of bulilding objects of the classs is that it works. " +
                "However, the problem with this approach should be pretty obvious.";

        SpellCheckResult result = spellChecker.checkTextSpelling(text, null);
        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/checkTextSpelling", path.substring(0, path.indexOf('?')));
        assertEquals(1, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertNotNull(result);
        assertNotNull(result.getCorrections());
        assertNotNull(result.getSourceSentences());
        assertNotNull(result.getProcessedSentences());
        assertEquals(result.getProcessedSentences().size(), result.getSourceSentences().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkTextSpelling_whenTextIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        spellChecker.checkTextSpelling(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkTextSpelling_whenTextIsEmpty_shouldThrowIllegalArgumentException() throws IntellexerException {
        spellChecker.checkTextSpelling("", null);
    }

    @Test(expected = IntellexerException.class)
    public void checkTextSpelling_whenDeserializationFailed_shouldThrowIntellexerException() throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        spellChecker.checkTextSpelling("test text", null);
    }

}