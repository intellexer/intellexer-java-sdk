package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseIT;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.exception.ServerErrorIntellexerException;
import by.effectivesoft.intellexer.model.param.AnalyzeSentimentsParams;
import by.effectivesoft.intellexer.model.sentiment.AnalyzeSentimentsResult;
import by.effectivesoft.intellexer.util.IntellexerTestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.*;

public class SentimentAnalyzerIT extends BaseIT {
    private SentimentAnalyzer analyzer;

    @Before
    public void setUp() {
        analyzer = new SentimentAnalyzer(client);
    }

    @Test
    public void analyzeSentiments_whenValidReviewsAndEmptyParameters_shouldReturnNotEmptyResultWithValidData()
            throws IntellexerException {
        AnalyzeSentimentsParams params = new AnalyzeSentimentsParams.Builder().build();

        AnalyzeSentimentsResult result = analyzer.analyzeSentiments(IntellexerTestUtils.buildListOfReviews(), params);

        assertNotNull(result);
        assertNotNull(result.getSentences());
        assertNotNull(result.getSentiments());
        assertNull(result.getOntology());
        assertNull(result.getOpinions());
        assertTrue(result.getSentences().size() == 3);
        assertTrue(result.getSentiments().get(0).getId().equals("12"));
        assertTrue(result.getSentiments().get(1).getId().equals("45"));
    }

    @Test
    public void analyzeSentiments_whenValidReviewsAndOntologyHotels_shouldReturnNotEmptyResultWithValidData()
            throws IntellexerException {
        AnalyzeSentimentsParams params = new AnalyzeSentimentsParams.Builder()
                .setOntology("hotels")
                .build();

        AnalyzeSentimentsResult result = analyzer.analyzeSentiments(IntellexerTestUtils.buildListOfReviews(), params);

        assertNotNull(result);
        assertNull(result.getSentences());
        assertNotNull(result.getSentiments());
        assertNotNull(result.getOntology());
        assertNotNull(result.getOpinions());
        assertEquals("hotels", result.getOntology());
        assertEquals("12", result.getSentiments().get(0).getId());
        assertEquals("45", result.getSentiments().get(1).getId());
    }

    @Test
    public void analyzeSentiments_whenValidReviewsAndOntologyRestaurantsLoadSentencesTrue_shouldReturnNotEmptyResultWithData()
            throws IntellexerException {
        AnalyzeSentimentsParams params = new AnalyzeSentimentsParams.Builder()
                .setOntology("restaurants")
                .setLoadSentences(true)
                .build();

        AnalyzeSentimentsResult result = analyzer.analyzeSentiments(IntellexerTestUtils.buildListOfReviews(), params);

        assertNotNull(result);
        assertNotNull(result.getSentences());
        assertNotNull(result.getSentiments());
        assertNotNull(result.getOntology());
        assertNotNull(result.getOpinions());
        assertEquals("restaurants", result.getOntology());
        assertTrue(result.getSentences().size() == 3);
        assertEquals("12", result.getSentiments().get(0).getId());
        assertEquals("45", result.getSentiments().get(1).getId());
    }

    @Test(expected = ServerErrorIntellexerException.class)
    public void analyzeSentiments_whenAnalyzingValidReviewsAndNotExistingOntology_shouldThrowServerErrorIntellexerException()
            throws Exception {

        AnalyzeSentimentsParams params = new AnalyzeSentimentsParams.Builder()
                .setOntology("test")
                .setLoadSentences(true)
                .build();

        analyzer.analyzeSentiments(IntellexerTestUtils.buildListOfReviews(), params);
    }


    @Test
    public void sentimentAnalyzerOntologies_whenMethodCalled_shouldReturnListOfOntologies() throws Exception {
        List<String> ontologies = analyzer.sentimentAnalyzerOntologies();

        assertNotNull(ontologies);
        assertTrue(ontologies.size() == 3);
        assertTrue(ontologies.contains("Hotels"));
    }

}