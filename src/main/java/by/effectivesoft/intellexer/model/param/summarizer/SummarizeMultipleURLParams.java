package by.effectivesoft.intellexer.model.param.summarizer;

import java.util.Map;

/**
 * Request parameters required to multiple URLs
 */
public class SummarizeMultipleURLParams extends SummarizeParams {
    private final String relatedFactsRequest;
    private final Integer maxRelatedFactsConcepts;
    private final Integer maxRelatedFactsSentences;

    private SummarizeMultipleURLParams(Builder builder) {
        super(builder);
        relatedFactsRequest = builder.relatedFactsRequest;
        maxRelatedFactsConcepts = builder.maxRelatedFactsConcepts;
        maxRelatedFactsSentences = builder.maxRelatedFactsSentences;
    }

    /**
     * Get query to extract facts and concepts related to it
     *
     * @return Query to extract facts and concepts related to it
     */
    public String getRelatedFactsRequest() {
        return relatedFactsRequest;
    }

    /**
     * Get max number of related facts/concepts to return
     *
     * @return Max number of related facts/concepts to return
     */
    public Integer getMaxRelatedFactsConcepts() {
        return maxRelatedFactsConcepts;
    }

    /**
     * Get max number of sample sentences for each related fact/concept
     *
     * @return Max number of sample sentences for each related fact/concept
     */
    public Integer getMaxRelatedFactsSentences() {
        return maxRelatedFactsSentences;
    }

    @Override
    public Map<String, String> toParamMap() {
        Map<String, String> params = super.toParamMap();
        if (relatedFactsRequest != null) {
            params.put("relatedFactsRequest", relatedFactsRequest);
        }
        if (maxRelatedFactsConcepts != null) {
            params.put("maxRelatedFactsConcepts", maxRelatedFactsConcepts.toString());
        }
        if (maxRelatedFactsSentences != null) {
            params.put("maxRelatedFactsSentences", maxRelatedFactsSentences.toString());
        }
        return params;
    }

    /**
     * Builder class to construct a {@link SummarizeMultipleURLParams} instance
     */
    public static class Builder extends BaseBuilder<Builder> {
        private String relatedFactsRequest;
        private Integer maxRelatedFactsConcepts;
        private Integer maxRelatedFactsSentences;

        public Builder() {
            super(Builder.class);
        }

        /**
         * Set query to extract facts and concepts related to it
         *
         * @param relatedFactsRequest Query to extract facts and concepts related to it
         * @return Builder
         */
        public Builder setRelatedFactsRequest(String relatedFactsRequest) {
            this.relatedFactsRequest = relatedFactsRequest;
            return this;
        }

        /**
         * Set max number of related facts/concepts to return
         *
         * @param maxRelatedFactsConcepts Max number of related facts/concepts to return
         * @return Builder
         */
        public Builder setMaxRelatedFactsConcepts(Integer maxRelatedFactsConcepts) {
            this.maxRelatedFactsConcepts = maxRelatedFactsConcepts;
            return this;
        }

        /**
         * Set max number of sample sentences for each related fact/concept
         *
         * @param maxRelatedFactsSentences Max number of sample sentences for each related fact/concept
         * @return Builder
         */
        public Builder setMaxRelatedFactsSentences(Integer maxRelatedFactsSentences) {
            this.maxRelatedFactsSentences = maxRelatedFactsSentences;
            return this;
        }

        @Override
        public SummarizeMultipleURLParams build() {
            return new SummarizeMultipleURLParams(this);
        }
    }


}
