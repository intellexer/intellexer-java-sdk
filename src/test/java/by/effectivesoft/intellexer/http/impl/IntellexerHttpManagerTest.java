package by.effectivesoft.intellexer.http.impl;

import by.effectivesoft.intellexer.BaseTest;
import by.effectivesoft.intellexer.exception.HttpIntellexerException;
import by.effectivesoft.intellexer.http.HttpManager;
import by.effectivesoft.intellexer.util.IntellexerTestUtils;
import com.squareup.okhttp.mockwebserver.MockResponse;
import org.junit.Before;
import org.junit.Test;

public class IntellexerHttpManagerTest extends BaseTest {
    private HttpManager httpManager;

    @Before
    public void setUp() throws Exception {
        httpManager = new IntellexerHttpManager();
    }

    @Test(expected = HttpIntellexerException.class)
    public void post_whenBadRequest_shouldThrowHttpIntellexerException() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody(IntellexerTestUtils.readTestResponse("api-error.json"))
        );

        httpManager.post(getServerURL() + "/test");
    }

    @Test(expected = HttpIntellexerException.class)
    public void post_whenAPIAuthFailed_shouldThrowHttpIntellexerException() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(401)
                .setBody(IntellexerTestUtils.readTestResponse("api-error.json"))
        );

        httpManager.post(getServerURL() + "/test");
    }

    @Test(expected = HttpIntellexerException.class)
    public void post_whenRequestIsForbidden_shouldThrowHttpIntellexerException() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(403)
                .setBody(IntellexerTestUtils.readTestResponse("api-error.json"))
        );

        httpManager.post(getServerURL() + "/test");
    }

    @Test(expected = HttpIntellexerException.class)
    public void post_whenServerError_shouldThrowHttpIntellexerException() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .setBody(IntellexerTestUtils.readTestResponse("api-error.json"))
        );

        httpManager.post(getServerURL() + "/test");
    }
}