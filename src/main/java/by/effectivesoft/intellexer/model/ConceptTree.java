package by.effectivesoft.intellexer.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class ConceptTree {
    private List<ConceptTree> children;
    private Boolean mainPhase;
    private List<Integer> sentenceIds;
    private Double status;
    private String text;
    private Double weight;

    public ConceptTree() {
        //required for Jackson JSON Processor
    }

    /**
     * Get children list – concept tree that consists of root nodes
     * (for ex. retrieval) and children nodes (for ex. information retrieval)
     *
     * @return Children list
     */
    public List<ConceptTree> getChildren() {
        return children;
    }

    /**
     * Set children list – concept tree that consists of root nodes
     * (for ex. retrieval) and children nodes (for ex. information retrieval)
     *
     * @param children Children list
     */
    public void setChildren(List<ConceptTree> children) {
        this.children = children;
    }

    /**
     * Get meaningful/important concepts used in named entity relations tree
     *
     * @return Get meaningful/important concepts flag
     */
    @JsonGetter("mp")
    public Boolean getMainPhase() {
        return mainPhase;
    }

    /**
     * Set meaningful/important concepts used in named entity relations tree
     *
     * @param mainPhase Get meaningful/important concepts flag
     */
    @JsonSetter("mp")
    public void setMainPhase(Boolean mainPhase) {
        this.mainPhase = mainPhase;
    }

    /**
     * Get list of sentence identifiers containing detected entities
     *
     * @return List of sentence identifiers
     */
    public List<Integer> getSentenceIds() {
        return sentenceIds;
    }

    /**
     * Set list of sentence identifiers containing detected entities
     *
     * @param sentenceIds List of sentence identifiers
     */
    public void setSentenceIds(List<Integer> sentenceIds) {
        this.sentenceIds = sentenceIds;
    }

    /**
     * Get status. Concept value change from 0 to 1, if the concept was selected for Rearrange operation
     *
     * @return Status value
     */
    @JsonGetter("st")
    public Double getStatus() {
        return status;
    }

    /**
     * Set status. Concept value change from 0 to 1, if the concept was selected for Rearrange operation
     *
     * @param status Status value
     */
    @JsonSetter("st")
    public void setStatus(Double status) {
        this.status = status;
    }

    /**
     * Get concept text
     *
     * @return Concept text
     */
    public String getText() {
        return text;
    }

    /**
     * Set concept text
     *
     * @param text Concept text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get concept weight
     *
     * @return Concept weight
     */
    @JsonGetter("w")
    public Double getWeight() {
        return weight;
    }

    /**
     * Set concept weight
     *
     * @param weight Concept weight
     */
    @JsonSetter("w")
    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
