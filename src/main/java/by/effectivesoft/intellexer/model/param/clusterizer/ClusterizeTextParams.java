package by.effectivesoft.intellexer.model.param.clusterizer;

import java.util.Map;

/**
 * Request parameters required to clusterize text
 */
public class ClusterizeTextParams extends ClusterizeParams {
    private final Integer textStreamLength;

    private ClusterizeTextParams(Builder builder) {
        super(builder);
        this.textStreamLength = builder.textStreamLength;
    }

    @Override
    public Map<String, String> toParamMap() {
        Map<String, String> params = super.toParamMap();
        if (textStreamLength != null) {
            params.put("textStreamLength", textStreamLength.toString());
        }
        return params;
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
     * Builder class to construct a {@link ClusterizeTextParams} instance
     */
    public static class Builder extends BaseBuilder<Builder> {
        private Integer textStreamLength;

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

        @Override
        public ClusterizeTextParams build() {
            return new ClusterizeTextParams(this);
        }
    }
}
