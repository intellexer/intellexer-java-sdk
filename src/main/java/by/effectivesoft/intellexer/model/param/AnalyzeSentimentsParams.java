package by.effectivesoft.intellexer.model.param;

import java.util.HashMap;
import java.util.Map;

/**
 * Request parameters required to analyze sentiments
 */
public class AnalyzeSentimentsParams extends BaseParams {
    private final String ontology;
    private final Boolean loadSentences;

    private AnalyzeSentimentsParams(Builder builder) {
        this.ontology = builder.ontology;
        this.loadSentences = builder.loadSentences;
    }


    /**
     * Get selected ontology
     * @return Ontology
     */
    public String getOntology() {
        return ontology;
    }

    /**
     * Get load sentences flag
     * @return Load sentences flag
     */
    public Boolean getLoadSentences() {
        return loadSentences;
    }

    @Override
    public Map<String, String> toParamMap() {
        Map<String, String> params = new HashMap<>();
        if (ontology != null) {
            params.put("ontology", ontology);
        }
        if (loadSentences != null) {
            params.put("loadSentences", loadSentences.toString());
        }
        return params;
    }

    /**
     * Builder class to construct a {@link AnalyzeSentimentsParams} instance
     */
    public static class Builder {
        private String ontology;
        private Boolean loadSentences;

        /**
         * Specify which of the existing ontologies will be used to group the results
         * @param ontology Ontology to be used
         * @return Builder
         */
        public Builder setOntology(String ontology) {
            this.ontology = ontology;
            return this;
        }

        /**
         * Set load source sentences (FALSE by default)
         * @param loadSentences if TRUE all sentences will be included in the result
         * @return Builder
         */
        public Builder setLoadSentences(Boolean loadSentences) {
            this.loadSentences = loadSentences;
            return this;
        }

        public AnalyzeSentimentsParams build() {
            return new AnalyzeSentimentsParams(this);
        }
    }

}
