package by.effectivesoft.intellexer;

import com.squareup.okhttp.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

public abstract class BaseTest {
    protected static final String apiKey = "TEST_API_KEY";
    protected IntellexerClient client;
    protected MockWebServer mockWebServer;

    @Before
    public void baseSetUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        client = new IntellexerClient(apiKey, getServerURL()
        );
    }

    @After
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    protected String getServerURL() {
        return "http://" + mockWebServer.getHostName() + ":" + mockWebServer.getPort();
    }
}
