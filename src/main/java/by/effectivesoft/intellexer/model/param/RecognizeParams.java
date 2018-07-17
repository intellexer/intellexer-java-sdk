package by.effectivesoft.intellexer.model.param;

import java.util.HashMap;
import java.util.Map;

/**
 * Request parameters required to language recognition
 */
public class RecognizeParams extends BaseParams {
    private final Boolean loadNamedEntities;
    private final Boolean loadRelationsTree;
    private final Boolean loadSentences;

    public RecognizeParams(Builder builder) {
        this.loadNamedEntities = builder.loadNamedEntities;
        this.loadRelationsTree = builder.loadRelationsTree;
        this.loadSentences = builder.loadSentences;
    }

    /**
     * Get load named entities (FALSE by default) flag
     *
     * @return Load named entities flag
     */
    public Boolean getLoadNamedEntities() {
        return loadNamedEntities;
    }

    /**
     * Get load named entities (FALSE by default) flag
     *
     * @return Load relations tree flag
     */
    public Boolean getLoadRelationsTree() {
        return loadRelationsTree;
    }

    /**
     * Get load source sentences (FALSE by default) flag
     *
     * @return Load sentences flag
     */
    public Boolean getLoadSentences() {
        return loadSentences;
    }

    @Override
    public Map<String, String> toParamMap() {
        Map<String, String> params = new HashMap<>();
        if (loadNamedEntities != null) {
            params.put("loadNamedEntities", loadNamedEntities.toString());
        }
        if (loadRelationsTree != null) {
            params.put("loadRelationsTree", loadRelationsTree.toString());
        }
        if (loadSentences != null) {
            params.put("loadSentences", loadSentences.toString());
        }
        return params;
    }

    /**
     * Builder class to construct a {@link RecognizeParams} instance
     */
    public static class Builder {
        private Boolean loadNamedEntities;
        private Boolean loadRelationsTree;
        private Boolean loadSentences;

        /**
         * Set load named entities (FALSE by default) flag
         *
         * @param loadNamedEntities if TRUE all named entities will be included in the result
         * @return Builder
         */
        public Builder setLoadNamedEntities(Boolean loadNamedEntities) {
            this.loadNamedEntities = loadNamedEntities;
            return this;
        }

        /**
         * Set load tree of relations (FALSE by default) flag
         *
         * @param loadRelationsTree if TRUE tree of relations will be included in the result
         * @return Builder
         */
        public Builder setLoadRelationsTree(Boolean loadRelationsTree) {
            this.loadRelationsTree = loadRelationsTree;
            return this;
        }

        /**
         * Set load source sentences (FALSE by default) flag
         *
         * @param loadSentences if TRUE tree all sentences will be included in the result
         * @return Builder
         */
        public Builder setLoadSentences(Boolean loadSentences) {
            this.loadSentences = loadSentences;
            return this;
        }

        public RecognizeParams build() {
            return new RecognizeParams(this);
        }
    }
}
