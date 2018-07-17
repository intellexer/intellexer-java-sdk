package by.effectivesoft.intellexer.model;

import java.util.List;

public class Option {
    private List<String> topics;
    private List<ReorderConcept> reorderConcepts;
    private List<ReorderConcept> reorderNamedEntities;

    public Option() {
        //required for Jackson JSON Processor
    }

    /**
     * Get list of summary topics
     *
     * @return List of topics
     */
    public List<String> getTopics() {
        return topics;
    }

    /**
     * Set list of summary topics
     *
     * @param topics List of topics
     */
    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    /**
     * Get list of concepts to reorder summary
     *
     * @return List of concepts
     */
    public List<ReorderConcept> getReorderConcepts() {
        return reorderConcepts;
    }

    /**
     * Set list of concepts to reorder summary
     *
     * @param reorderConcepts List of concepts
     */
    public void setReorderConcepts(List<ReorderConcept> reorderConcepts) {
        this.reorderConcepts = reorderConcepts;
    }

    /**
     * Get list of named entities to reorder summary
     *
     * @return List of named entities
     */
    public List<ReorderConcept> getReorderNamedEntities() {
        return reorderNamedEntities;
    }

    /**
     * Set list of named entities to reorder summary
     *
     * @param reorderNamedEntities List of named entities
     */
    public void setReorderNamedEntities(List<ReorderConcept> reorderNamedEntities) {
        this.reorderNamedEntities = reorderNamedEntities;
    }

}
