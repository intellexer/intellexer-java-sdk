package by.effectivesoft.intellexer.model.summarizer;

import by.effectivesoft.intellexer.model.ConceptTree;
import by.effectivesoft.intellexer.model.Document;
import by.effectivesoft.intellexer.model.DocumentStructure;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class SummarizeResult {
    private Document document;
    private DocumentStructure structure;
    private List<String> topics;
    private List<SummaryItem> items;
    private Integer totalItemsCount;
    private ConceptTree conceptTree;
    private ConceptTree namedEntityTree;

    public SummarizeResult() {
        //required for Jackson JSON Processor
    }

    /**
     * Get information about the text
     *
     * @return Information about the text
     */
    @JsonGetter("summarizerDoc")
    public Document getDocument() {
        return document;
    }

    /**
     * Set information about the text
     *
     * @param document Information about the text
     */
    @JsonSetter("summarizerDoc")
    public void setDocument(Document document) {
        this.document = document;
    }

    /**
     * Get document structure
     *
     * @return Document structure
     */
    public DocumentStructure getStructure() {
        return structure;
    }

    /**
     * Set document structure
     *
     * @param structure Document structure
     */
    public void setStructure(DocumentStructure structure) {
        this.structure = structure;
    }

    /**
     * Get list of detected document topics
     *
     * @return List of detected document topics
     */
    public List<String> getTopics() {
        return topics;
    }

    /**
     * Set list of detected document topics
     *
     * @param topics List of detected document topics
     */
    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    /**
     * Get summary items (important document sentences)
     *
     * @return List of summary items
     */
    public List<SummaryItem> getItems() {
        return items;
    }

    /**
     * Set summary items (important document sentences)
     *
     * @param items List of summary items
     */
    public void setItems(List<SummaryItem> items) {
        this.items = items;
    }

    /**
     * Get total number of processed sentences
     *
     * @return Total number of processed sentences
     */
    public Integer getTotalItemsCount() {
        return totalItemsCount;
    }

    /**
     * Set total number of processed sentences
     *
     * @param totalItemsCount Total number of processed sentences
     */
    public void setTotalItemsCount(Integer totalItemsCount) {
        this.totalItemsCount = totalItemsCount;
    }

    /**
     * Get tree of important document concepts
     *
     * @return Tree of important document concepts
     */
    public ConceptTree getConceptTree() {
        return conceptTree;
    }

    /**
     * Set tree of important document concepts
     *
     * @param conceptTree Tree of important document concepts
     */
    public void setConceptTree(ConceptTree conceptTree) {
        this.conceptTree = conceptTree;
    }

    /**
     * Get tree of relations among the detected entities
     *
     * @return Tree of relations among the detected entities
     */
    public ConceptTree getNamedEntityTree() {
        return namedEntityTree;
    }

    /**
     * Set tree of relations among the detected entities
     *
     * @param namedEntityTree Tree of relations among the detected entities
     */
    public void setNamedEntityTree(ConceptTree namedEntityTree) {
        this.namedEntityTree = namedEntityTree;
    }

    public String summary() {
        StringBuilder builder = new StringBuilder();
        for (SummaryItem item : items) {
            builder.append(item.getText()).append(" ");
        }
        return builder.toString();
    }
}
