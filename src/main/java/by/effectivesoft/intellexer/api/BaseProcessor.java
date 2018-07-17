package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.IntellexerClient;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseProcessor {
    public static final String BASE_API_URL = "http://api.intellexer.com";
    private static final String API_ENDPOINT_PARAM_FORMAT = "%s?apiKey=%s";

    protected IntellexerClient client;
    protected ObjectMapper objectMapper;

    protected BaseProcessor(IntellexerClient client) {
        this.client = client;
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    protected String buildEndpointURL(String endpoint) {
        return client.getApiURL() + String.format(API_ENDPOINT_PARAM_FORMAT, endpoint, client.getApiKey());
    }

}
