package by.effectivesoft.intellexer.model.param.summarizer;

import java.util.Map;

/**
 * Request parameters required to summarize URL
 */
public class SummarizeURLParams extends SummarizeParams {
    private final Boolean useCache;
    private final Integer textStreamLength;
    private final Boolean wrapConcepts;

    protected SummarizeURLParams(Builder builder) {
        super(builder);
        this.useCache = builder.useCache;
        this.textStreamLength = builder.textStreamLength;
        this.wrapConcepts = builder.wrapConcepts;
    }

    /**
     * Get use cache flag
     *
     * @return Use cache flag
     */
    public Boolean getUseCache() {
        return useCache;
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
        if (useCache != null) {
            params.put("useCache", useCache.toString());
        }
        if (textStreamLength != null) {
            params.put("textStreamLength", textStreamLength.toString());
        }
        if (wrapConcepts != null) {
            params.put("wrapConcepts", wrapConcepts.toString());
        }
        return params;
    }

    /**
     * Builder class to construct a {@link SummarizeURLParams} instance
     */
    public static class Builder extends BaseBuilder<Builder> {
        private Boolean useCache;
        private Integer textStreamLength;
        private Boolean wrapConcepts;

        public Builder() {
            super(Builder.class);
        }

        /**
         * Set use cache flag
         *
         * @param useCache if TRUE, document content will be loaded from cache if there is any
         * @return Builder
         */
        public Builder setUseCache(Boolean useCache) {
            this.useCache = useCache;
            return this;
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
        public SummarizeURLParams build() {
            return new SummarizeURLParams(this);
        }
    }
}
