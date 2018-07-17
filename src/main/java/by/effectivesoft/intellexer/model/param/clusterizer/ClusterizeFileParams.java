package by.effectivesoft.intellexer.model.param.clusterizer;

/**
 * Request parameters required to clusterize file
 */
public class ClusterizeFileParams extends ClusterizeParams {
    private ClusterizeFileParams(Builder builder) {
        super(builder);
    }

    /**
     * Builder class to construct a {@link ClusterizeFileParams} instance
     */
    public static class Builder extends BaseBuilder<Builder> {
        public Builder() {
            super(Builder.class);
        }

        @Override
        public ClusterizeFileParams build() {
            return new ClusterizeFileParams(this);
        }
    }
}
