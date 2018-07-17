package by.effectivesoft.intellexer.model.param.clusterizer;

import java.util.Map;

/**
 * Request parameters required to clusterize URL
 */
public class ClusterizeURLParams extends ClusterizeParams {
    private final Boolean useCache;

    private ClusterizeURLParams(Builder builder) {
        super(builder);
        this.useCache = builder.useCache;
    }

    /**
     * Get use cache flag
     *
     * @return Use cache flag
     */
    public Boolean getUseCache() {
        return useCache;
    }

    @Override
    public Map<String, String> toParamMap() {
        Map<String, String> params = super.toParamMap();
        if (useCache != null) {
            params.put("useCache", useCache.toString());
        }
        return params;
    }

    /**
     * Builder class to construct a {@link ClusterizeURLParams} instance
     */
    public static class Builder extends BaseBuilder<Builder> {
        private Boolean useCache;

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

        @Override
        public ClusterizeURLParams build() {
            return new ClusterizeURLParams(this);
        }
    }
}
