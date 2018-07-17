package by.effectivesoft.intellexer;

/**
 * To run integration tests replace YOUR_API_KEY with your key
 */
public abstract class BaseIT {
    private final String API_KEY = "YOUR_API_KEY";

    protected IntellexerClient client;

    public BaseIT() {
        String apiKey = System.getProperty("apiKey");
        if (apiKey == null) {
            apiKey = API_KEY;
        }
        client = new IntellexerClient(apiKey);
    }

}
