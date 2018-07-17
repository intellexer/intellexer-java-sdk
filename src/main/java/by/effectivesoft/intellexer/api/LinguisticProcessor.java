package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.IntellexerClient;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.linguistic.Sentence;
import by.effectivesoft.intellexer.model.param.AnalyzeTextParams;

import java.io.IOException;
import java.util.List;

/**
 * Intellexer Linguistic Processor is used to parse the input text and to extract multiple kinds of relations, for
 * example, syntactic (noun phrases, verb phrases, adjectival and adverbial phrases, etc.) and semantic
 * (subject-verb-object; Color, Direction, Degree, Effectiveness, etc.) ones. The output of Linguistic Processor is a
 * semantic tree with certain semantic types of relations assigned to the links between the sentence elements.
 */
public class LinguisticProcessor extends BaseProcessor {
    private static final String ANALYZE_TEXT_PATH = "/analyzeText";

    public LinguisticProcessor(IntellexerClient client) {
        super(client);
    }

    /**
     * Parse an input text stream and extract various linguistic information: detected sentences with their offsets in a
     * source text; text tokens (words of sentences) with their part of speech tags, offsets and lemmas (normal forms);
     * subject-verb-object semantic relations.
     *
     * @param text   Text to analyze
     * @param params Analyze text parameters
     * @return List of parsed sentences
     * @throws IntellexerException      if API call or response deserialization failed
     * @throws IllegalArgumentException if <code>text</code> parameter is null or empty
     */
    public List<Sentence> analyzeText(String text, AnalyzeTextParams params) throws IntellexerException {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text may not be null or empty");
        }
        try {
            String result = client.getHttpManager().post(
                    buildEndpointURL(ANALYZE_TEXT_PATH),
                    params,
                    text
            );
            return objectMapper.readValue(result, LinguisticSentences.class).getSentences();
        } catch (IOException e) {
            throw new IntellexerException(e);
        }
    }

    private static class LinguisticSentences {
        private List<Sentence> sentences;

        public LinguisticSentences() {
            //required for Jackson JSON Processor
        }

        public List<Sentence> getSentences() {
            return sentences;
        }

        public void setSentences(List<Sentence> sentences) {
            this.sentences = sentences;
        }
    }


}
