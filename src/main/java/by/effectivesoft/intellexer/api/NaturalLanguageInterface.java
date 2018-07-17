package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.IntellexerClient;
import by.effectivesoft.intellexer.exception.IntellexerException;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;

import java.io.IOException;

/**
 * Natural Language Interface provides natural, human-like interaction with any application. This makes the work
 * effective, as it eliminates the necessity to study special syntax of queries (e.g., Boolean operators used in Google)
 * and allows for detailed and precise description of the requested information.
 */
public class NaturalLanguageInterface extends BaseProcessor {
    private static final String CONVERT_QUERY_PATH = "/convertQueryToBool";

    public NaturalLanguageInterface(IntellexerClient client) {
        super(client);
    }

    /**
     * Convert user query in English to a set of terms and concepts joined by logical operators.
     *
     * @param text Query to convert
     * @return Set of terms and concepts joined by logical operators
     * @throws IntellexerException if API call or response deserialization failed
     */
    public String convertQueryToBool(String text) throws IntellexerException {
        try {
            return client.getHttpManager().post(
                    buildEndpointURL(CONVERT_QUERY_PATH),
                    text,
                    new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
            );
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }
}
