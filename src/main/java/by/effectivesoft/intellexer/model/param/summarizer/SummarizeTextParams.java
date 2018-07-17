package by.effectivesoft.intellexer.model.param.summarizer;

import java.util.Map;

/**
 * Request parameters required to summarize text
 */
public class SummarizeTextParams extends SummarizeParams {
    private final Integer textStreamLength;
    private final Boolean wrapConcepts;

    private SummarizeTextParams(Builder builder) {
        super(builder);
        this.textStreamLength = builder.textStreamLength;
        this.wrapConcepts = builder.wrapConcepts;
    }

    /**
     * Get content length in bytes
     *
     * @return Content length in bytes
     */
    public Integer getTextStreamLength() {
        return textStreamLength;
    }

    /**
     * Get wrap concepts flag
     *
     * @return Wrap concepts flag
     */
    public Boolean getWrapConcepts() {
        return wrapConcepts;
    }

    @Override
    public Map<String, String> toParamMap() {
        Map<String, String> params = super.toParamMap();
        if (textStreamLength != null) {
            params.put("textStreamLength", textStreamLength.toString());
        }
        if (wrapConcepts != null) {
            params.put("wrapConcepts", wrapConcepts.toString());
        }
        return params;
    }

    /**
     * Builder class to construct a {@link SummarizeTextParams} instance
     */
    public static class Builder extends BaseBuilder<Builder> {
        private Integer textStreamLength;
        private Boolean wrapConcepts;

        public Builder() {
            super(Builder.class);
        }

        /**
         * Set content length in bytes
         *
         * @param textStreamLength Content length in bytes
         * @return Builder
         */
        public Builder setTextStreamLength(Integer textStreamLength) {
            this.textStreamLength = textStreamLength;
            return this;
        }

        /**
         * Set mark concepts found in the summary with HTML bold tags (FALSE by default) flag
         *
         * @param wrapConcepts Wrap concepts flag
         * @return Builder
         */
        public Builder setWrapConcepts(Boolean wrapConcepts) {
            this.wrapConcepts = wrapConcepts;
            return this;
        }

        @Override
        public SummarizeTextParams build() {
            return new SummarizeTextParams(this);
        }
    }
}
