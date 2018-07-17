package by.effectivesoft.intellexer.model.param.summarizer;

import by.effectivesoft.intellexer.exception.IntellexerRuntimeException;
import by.effectivesoft.intellexer.model.DocumentStructure;
import by.effectivesoft.intellexer.model.Option;
import by.effectivesoft.intellexer.model.param.BaseParams;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Base request parameters required for summarization
 */
public class SummarizeParams extends BaseParams {
    private final Boolean loadConceptsTree;
    private final Boolean loadNamedEntityTree;
    private final Integer summaryRestriction;
    private final Boolean usePercentRestriction;
    private final Integer conceptsRestriction;
    private final DocumentStructure structure;
    private final Integer returnedTopicsCount;
    private final Boolean fullTextTrees;
    private final Option options;

    protected SummarizeParams(BaseBuilder builder) {
        this.loadConceptsTree = builder.loadConceptsTree;
        this.loadNamedEntityTree = builder.loadNamedEntityTree;
        this.summaryRestriction = builder.summaryRestriction;
        this.usePercentRestriction = builder.usePercentRestriction;
        this.conceptsRestriction = builder.conceptsRestriction;
        this.structure = builder.structure;
        this.returnedTopicsCount = builder.returnedTopicsCount;
        this.fullTextTrees = builder.fullTextTrees;
        this.options = builder.options;
    }


    /**
     * Get load a tree of concepts flag
     *
     * @return Load a tree of concepts flag
     */
    public Boolean getLoadConceptsTree() {
        return loadConceptsTree;
    }

    /**
     * Get load a tree of Named Entities flag
     *
     * @return Load a tree of Named Entities  flag
     */
    public Boolean getLoadNamedEntityTree() {
        return loadNamedEntityTree;
    }

    /**
     * Get summary restriction: size of a summary measured in sentences
     *
     * @return Size of a summary measured in sentences
     */
    public Integer getSummaryRestriction() {
        return summaryRestriction;
    }

    /**
     * Get use percentage flag
     * if TRUE the number of sentences in the original text will be used instead of the
     * exact number of sentences
     *
     * @return Use percentage restriction flag
     */
    public Boolean getUsePercentRestriction() {
        return usePercentRestriction;
    }

    /**
     * Get concepts restriction: length of a concept tree
     *
     * @return Length of a concept tree
     */
    public Integer getConceptsRestriction() {
        return conceptsRestriction;
    }

    /**
     * Get structure of the document
     *
     * @return Structure of the document
     */
    public DocumentStructure getStructure() {
        return structure;
    }

    /**
     * Get max count of document topics to return
     *
     * @return Max count of document topics to return
     */
    public Integer getReturnedTopicsCount() {
        return returnedTopicsCount;
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
        if (loadConceptsTree != null) {
            params.put("loadConceptsTree", loadConceptsTree.toString());
        }
        if (loadNamedEntityTree != null) {
            params.put("loadNamedEntityTree", loadNamedEntityTree.toString());
        }
        if (summaryRestriction != null) {
            params.put("summaryRestriction", summaryRestriction.toString());
        }
        if (usePercentRestriction != null) {
            params.put("usePercentRestriction", usePercentRestriction.toString());
        }
        if (conceptsRestriction != null) {
            params.put("conceptsRestriction", conceptsRestriction.toString());
        }
        if (structure != null) {
            params.put("structure", structure.getName());
        }
        if (returnedTopicsCount != null) {
            params.put("returnedTopicsCount", returnedTopicsCount.toString());
        }
        if (fullTextTrees != null) {
            params.put("fullTextTrees", fullTextTrees.toString());
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
     * Base builder class to construct a {@link SummarizeParams} instance
     */
    protected static class BaseBuilder<T> {
        private final Class<T> builderClass;
        private Boolean loadConceptsTree;
        private Boolean loadNamedEntityTree;
        private Integer summaryRestriction;
        private Boolean usePercentRestriction;
        private Integer conceptsRestriction;
        private DocumentStructure structure;
        private Integer returnedTopicsCount;
        private Boolean fullTextTrees;
        private Option options;

        public BaseBuilder(Class<T> builderClass) {
            this.builderClass = builderClass;
        }

        /**
         * Set load a tree of concepts (FALSE by default) flag
         *
         * @param loadConceptsTree if TRUE tree of concepts will be included in the result
         * @return Builder
         */
        public T setLoadConceptsTree(Boolean loadConceptsTree) {
            this.loadConceptsTree = loadConceptsTree;
            return builderClass.cast(this);
        }

        /**
         * Set load a tree of Named Entities (FALSE by default) flag
         *
         * @param loadNamedEntityTree if TRUE tree of named entities will be included in the result
         * @return Builder
         */
        public T setLoadNamedEntityTree(Boolean loadNamedEntityTree) {
            this.loadNamedEntityTree = loadNamedEntityTree;
            return builderClass.cast(this);
        }

        /**
         * Determine size of a summary measured in sentences
         *
         * @param summaryRestriction Size of a summary measured in sentences
         * @return Builder
         */
        public T setSummaryRestriction(Integer summaryRestriction) {
            this.summaryRestriction = summaryRestriction;
            return builderClass.cast(this);
        }

        /**
         * Set use percentage of the number of sentences in the original text instead of the exact number of sentences
         * flag
         *
         * @param usePercentRestriction if TRUE the number of sentences in the original text will be used instead of the
         *                              exact number of sentences
         * @return Builder
         */
        public T setUsePercentRestriction(Boolean usePercentRestriction) {
            this.usePercentRestriction = usePercentRestriction;
            return builderClass.cast(this);
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
         * Specify structure of the document (News Article, Research Paper, Patent or General)
         *
         * @param structure Structure of the document
         * @return Builder
         */
        public T setStructure(DocumentStructure structure) {
            this.structure = structure;
            return builderClass.cast(this);
        }

        /**
         * Determine max count of document topics to return
         *
         * @param returnedTopicsCount Max count of document topics to return
         * @return Builder
         */
        public T setReturnedTopicsCount(Integer returnedTopicsCount) {
            this.returnedTopicsCount = returnedTopicsCount;
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
         * Set summary topics, list of NE to reorder summary, list of concepts to reorder summary
         *
         * @param options Request options
         * @return Builder
         */
        public T setOptions(Option options) {
            this.options = options;
            return builderClass.cast(this);
        }

        public SummarizeParams build() {
            return new SummarizeParams(this);
        }
    }
}
