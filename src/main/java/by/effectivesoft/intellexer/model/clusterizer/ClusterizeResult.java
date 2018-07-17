package by.effectivesoft.intellexer.model.clusterizer;

import by.effectivesoft.intellexer.model.ConceptTree;

import java.util.List;

public class ClusterizeResult {
    private ConceptTree conceptTree;
    private List<String> sentences;

    public ClusterizeResult() {
        //required for Jackson JSON Processor
    }

    /**
     * Get tree of important document concepts
     *
     * @return Tree of concepts
     */
    public ConceptTree getConceptTree() {
        return conceptTree;
    }

    public void setConceptTree(ConceptTree conceptTree) {
        this.conceptTree = conceptTree;
    }

    /**
     * Get list of processed sentences
     *
     * @return List of processed sentences
     */
    public List<String> getSentences() {
        return sentences;
    }

    public void setSentences(List<String> sentences) {
        this.sentences = sentences;
    }
}
