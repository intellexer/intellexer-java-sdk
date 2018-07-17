package by.effectivesoft.intellexer;

import by.effectivesoft.intellexer.api.BaseProcessor;
import by.effectivesoft.intellexer.http.HttpManager;
import by.effectivesoft.intellexer.http.impl.IntellexerHttpManager;

public class IntellexerClient {
    private final String apiKey;
    private final String apiURL;
    private final HttpManager httpManager;

    public IntellexerClient(String apiKey) {
        this(apiKey, BaseProcessor.BASE_API_URL);
    }

    IntellexerClient(String apiKey, String apiURL) {
        this.apiKey = apiKey;
        this.apiURL = apiURL;
        this.httpManager = new IntellexerHttpManager();
    }

    /**
     * Get Intellexer API key
     *
     * @return API key
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Get Intellexer API URL
     *
     * @return API URL
     */
    public String getApiURL() {
        return apiURL;
    }

    public HttpManager getHttpManager() {
        return httpManager;
    }
}
