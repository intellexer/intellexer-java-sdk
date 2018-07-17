package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseTest;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.Language;
import by.effectivesoft.intellexer.util.IntellexerTestUtils;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class LanguageRecognizerTest extends BaseTest {
    private LanguageRecognizer languageRecognizer;

    @Before
    public void setUp() throws Exception {
        languageRecognizer = new LanguageRecognizer(client);
    }

    @Test
    public void recognizeLanguage_whenValidText_shouldSendPostRequestAndReturnNotEmptyData()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("language-recognizer/result.json"))
                .setResponseCode(200)
        );
        String text = "The good thing about this way of building objects of the class is that it works.";
        List<Language> languageList = languageRecognizer.recognizeLanguage(text);


        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/recognizeLanguage", path.substring(0, path.indexOf('?')));
        assertEquals(1, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertNotNull(languageList);
        assertTrue(!languageList.isEmpty());
        assertEquals("English", languageList.get(0).getLanguageName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void recognizeLanguage_whenTextIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        languageRecognizer.recognizeLanguage(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void recognizeLanguage_whenTextIsEmpty_shouldThrowIllegalArgumentException() throws IntellexerException {
        languageRecognizer.recognizeLanguage("");
    }

    @Test(expected = IntellexerException.class)
    public void recognizeLanguage_whenDeserializationFailed_shouldThrowIntellexerException() throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        languageRecognizer.recognizeLanguage("text");
    }

}