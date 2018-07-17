package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.IntellexerClient;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.param.AnalyzeSentimentsParams;
import by.effectivesoft.intellexer.model.sentiment.AnalyzeSentimentsResult;
import by.effectivesoft.intellexer.model.sentiment.Review;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.util.List;

/**
 * Intellexer Sentiment Analyzer is a powerful and efficient solution that automatically extracts sentiments
 * (positivity/negativity), opinion objects and emotions (liking, anger, disgust, etc.) from unstructured text
 * information. Besides, Intellexer Sentiment Analyzer can be successfully used for document sentiment classification
 * and review rating prediction tasks.
 */
public class SentimentAnalyzer extends BaseProcessor {
    private static final String ANALYZE_SENTIMENTS_PATH = "/analyzeSentiments";
    private static final String SENTIMENT_ANALYZER_ONTOLOGIES_PATH = "/sentimentAnalyzerOntologies";

    public SentimentAnalyzer(IntellexerClient client) {
        super(client);
    }

    /**
     * Perform sentiments analysis on reviews from the list
     *
     * @param reviews List of reviews
     * @param params  Analyze sentiments parameters
     * @return Result of analysis
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>review</code> parameter is null or contains null elements or empty
     */
    public AnalyzeSentimentsResult analyzeSentiments(List<Review> reviews,
                                                     AnalyzeSentimentsParams params) throws IntellexerException {
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        if (reviews == null || reviews.contains(null)) {
            throw new IllegalArgumentException("List of review may not be null or contain null");
        }
        if (reviews.isEmpty()){
            throw new IllegalArgumentException("List of review may not be empty");
        }
        try {
            String result = client.getHttpManager().post(
                    buildEndpointURL(ANALYZE_SENTIMENTS_PATH),
                    params,
                    objectMapper.writeValueAsString(reviews),
                    new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json")
            );
            return objectMapper.readValue(result, AnalyzeSentimentsResult.class);
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }

    /**
     * Get the list of available Sentiment Analyzer ontologies
     *
     * @return The list of available Sentiment Analyzer ontologies
     * @throws IntellexerException if API call or response deserialization failed
     */
    public List<String> sentimentAnalyzerOntologies() throws IntellexerException {
        try {
            String result = client.getHttpManager().get(buildEndpointURL(SENTIMENT_ANALYZER_ONTOLOGIES_PATH));
            return objectMapper.readValue(result, new TypeReference<List<String>>() {
            });
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }
}
