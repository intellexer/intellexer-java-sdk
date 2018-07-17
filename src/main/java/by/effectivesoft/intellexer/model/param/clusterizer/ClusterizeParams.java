package by.effectivesoft.intellexer.model.param.clusterizer;

import by.effectivesoft.intellexer.exception.IntellexerRuntimeException;
import by.effectivesoft.intellexer.model.Option;
import by.effectivesoft.intellexer.model.param.BaseParams;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Base request parameters required for clusterization
 */
public class ClusterizeParams extends BaseParams {
    private final Integer conceptsRestriction;
    private final Boolean fullTextTrees;
    private final Boolean loadSentences;
    private final Boolean wrapConcepts;
    private final Option options;

    protected ClusterizeParams(BaseBuilder builder) {
        this.conceptsRestriction = builder.conceptsRestriction;
        this.fullTextTrees = builder.fullTextTrees;
        this.loadSentences = builder.loadSentences;
        this.wrapConcepts = builder.wrapConcepts;
        this.options = builder.options;
    }

    /**
     * Get length of a concept tree
     *
     * @return Length of a concept tree
     */
    public Integer getConceptsRestriction() {
        return conceptsRestriction;
    }

    /**
     * Get load full text trees flag
     *
     * @return Load full text trees flag
     */
    public Boolean getFullTextTrees() {
        return fullTextTrees;
    }

    /**
     * Get load sentences flag
     *
     * @return Load sentences flag
     */
    public Boolean getLoadSentences() {
        return loadSentences;
    }

    /**
     * Get wrap concepts flag
     *
     * @return Wrap concepts flag
     */
    public Boolean getWrapConcepts() {
        return wrapConcepts;
    }

    /**
     * Get request options: summary topics, list of NE to reorder summary, list of concepts to reorder summary
     *
     * @return Options
     */
    public Option getOptions() {
        return options;
    }

    @Override
    public Map<String, String> toParamMap() {
        Map<String, String> params = new HashMap<>();
        if (conceptsRestriction != null) {
            params.put("conceptsRestriction", conceptsRestriction.toString());
        }
        if (fullTextTrees != null) {
            params.put("fullTextTrees", fullTextTrees.toString());
        }

        if (loadSentences != null) {
            params.put("loadSentences", loadSentences.toString());
        }
        if (wrapConcepts != null) {
            params.put("wrapConcepts", wrapConcepts.toString());
        }
        if (options != null) {
            String stringOptions;
            try {
                stringOptions = URLEncoder.encode(new ObjectMapper().writeValueAsString(options), "UTF-8");
                params.put("options", stringOptions);
            } catch (JsonProcessingException | UnsupportedEncodingException e) {
                throw new IntellexerRuntimeException();
            }
        }
        return params;
    }

    /**
     * Base builder class to construct a {@link ClusterizeParams} instance
     */
    protected static class BaseBuilder<T> {
        private final Class<T> builderClass;
        private Integer conceptsRestriction;
        private Boolean fullTextTrees;
        private Boolean loadSentences;
        private Boolean wrapConcepts;
        private Option options;

        public BaseBuilder(Class<T> builderClass) {
            this.builderClass = builderClass;
        }

        /**
         * Determine the length of a concept tree
         *
         * @param conceptsRestriction Length of a concept tree
         * @return Builder
         */
        public T setConceptsRestriction(Integer conceptsRestriction) {
            this.conceptsRestriction = conceptsRestriction;
            return builderClass.cast(this);
        }

        /**
         * Set load full text trees flag
         *
         * @param fullTextTrees if TRUE full text tree will be included to the result
         * @return Builder
         */
        public T setFullTextTrees(Boolean fullTextTrees) {
            this.fullTextTrees = fullTextTrees;
            return builderClass.cast(this);
        }


        /**
         * Set load all sentences flag
         *
         * @param loadSentences if TRUE all sentences will be included to the result
         * @return Builder
         */
        public T setLoadSentences(Boolean loadSentences) {
            this.loadSentences = loadSentences;
            return builderClass.cast(this);
        }

        /**
         * Set mark concepts found in the summary with HTML bold tags (FALSE by default) flag
         *
         * @param wrapConcepts Wrap concepts flag
         * @return Builder
         */
        public T setWrapConcepts(Boolean wrapConcepts) {
            this.wrapConcepts = wrapConcepts;
            return builderClass.cast(this);
        }

        /**
         * Set summary topics, list of NE to reorder summary, list of concepts to reorder summary
         *
         * @param options Request options
         * @return Builder
         */
        public T setOptions(Option options) {
            this.options = options;
            return builderClass.cast(this);
        }

        public ClusterizeParams build() {
            return new ClusterizeParams(this);
        }
    }

}
