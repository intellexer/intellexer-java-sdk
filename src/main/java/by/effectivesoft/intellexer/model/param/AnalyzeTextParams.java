package by.effectivesoft.intellexer.model.param;

import java.util.HashMap;
import java.util.Map;

/**
 * Request parameters required to analyze text
 */
public class AnalyzeTextParams extends BaseParams {
    private final Boolean loadSentences;
    private final Boolean loadTokens;
    private final Boolean loadRelations;

    /**
     * Constructs parameters that required to analyze text
     */
    private AnalyzeTextParams(Builder builder) {
        this.loadSentences = builder.loadSentences;
        this.loadTokens = builder.loadTokens;
        this.loadRelations = builder.loadRelations;
    }

    /**
     * Get load source sentences flag
     *
     * @return Load source sentences flag
     */
    public Boolean getLoadSentences() {
        return loadSentences;
    }

    /**
     * Get load information about words of sentences flag
     *
     * @return Load token flag
     */
    public Boolean getLoadTokens() {
        return loadTokens;
    }

    /**
     * Get load information about extracted semantic relations in sentences flag
     *
     * @return Load relations flag
     */
    public Boolean getLoadRelations() {
        return loadRelations;
    }

    @Override
    public Map<String, String> toParamMap() {
        Map<String, String> params = new HashMap<>();
        if (loadSentences != null) {
            params.put("loadSentences", loadSentences.toString());
        }
        if (loadTokens != null) {
            params.put("loadTokens", loadTokens.toString());
        }
        if (loadRelations != null) {
            params.put("loadRelations", loadRelations.toString());
        }
        return params;
    }

    /**
     * Builder class to construct a {@link AnalyzeTextParams} instance
     */
    public static class Builder {
        private Boolean loadSentences;
        private Boolean loadTokens;
        private Boolean loadRelations;

        /**
         * Set load source sentences (TRUE by default) flag
         *
         * @param loadSentences if TRUE all sentences will be included in the result
         * @return Builder
         */
        public Builder setLoadSentences(Boolean loadSentences) {
            this.loadSentences = loadSentences;
            return this;
        }

        /**
         * Set load information about words of sentences (TRUE by default) flag
         *
         * @param loadTokens if TRUE all tokens will be included in the result
         * @return Builder
         */
        public Builder setLoadTokens(Boolean loadTokens) {
            this.loadTokens = loadTokens;
            return this;
        }

        /**
         * Set load information about extracted semantic relations in sentences (TRUE by default)
         *
         * @param loadRelations if TRUE all semantic relations in sentences in will be included in the result
         * @return Builder
         */
        public Builder setLoadRelations(Boolean loadRelations) {
            this.loadRelations = loadRelations;
            return this;
        }

        public AnalyzeTextParams build() {
            return new AnalyzeTextParams(this);
        }
    }
}
