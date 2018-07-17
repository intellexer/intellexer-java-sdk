package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseIT;
import by.effectivesoft.intellexer.model.linguistic.Sentence;
import by.effectivesoft.intellexer.model.param.AnalyzeTextParams;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.*;

public class LinguisticProcessorIT extends BaseIT {

    private LinguisticProcessor processor;

    @Before
    public void setUp() {
        processor = new LinguisticProcessor(client);
    }

    @Test
    public void analyzeText_whenValidTextAndEmptyParameters_shouldReturnNotEmptyListOfSentencesWithData()
            throws Exception {
        String textToAnalyze = "Intellexer Summarizer has an unique feature";

        List<Sentence> sentences = processor.analyzeText(
                textToAnalyze,
                new AnalyzeTextParams.Builder().build()
        );

        assertNotNull(sentences);
        assertTrue(sentences.size() == 1);
        assertNotNull(sentences.get(0).getText());
        assertNotNull(sentences.get(0).getTokens());
        assertNotNull(sentences.get(0).getRelations());
        assertEquals(textToAnalyze, sentences.get(0).getText().getContent());
    }

    @Test
    public void analyzeText_whenValidTextAndAllParametersAreSetToFalse_shouldReturnNotEmptyListOfSentencesWithoutData()
            throws Exception {
        String textToAnalyze = "Intellexer Summarizer has an unique feature";
        AnalyzeTextParams params = new AnalyzeTextParams.Builder()
                .setLoadSentences(false)
                .setLoadTokens(false)
                .setLoadRelations(false)
                .build();
        List<Sentence> sentences = processor.analyzeText(textToAnalyze, params);
        assertNotNull(sentences);
        assertTrue(sentences.size() == 1);
        assertNull(sentences.get(0).getText());
        assertNull(sentences.get(0).getTokens());
        assertNotNull(sentences.get(0).getRelations());
        assertTrue(sentences.get(0).getRelations().isEmpty());
    }

}