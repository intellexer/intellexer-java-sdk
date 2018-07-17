package by.effectivesoft.intellexer.model.summarizer;

import by.effectivesoft.intellexer.model.ConceptTree;
import by.effectivesoft.intellexer.model.Document;
import by.effectivesoft.intellexer.model.DocumentStructure;

import java.util.List;

public class SummarizeMultipleDocumentResult {
    private List<Document> documents;
    private List<String> topics;
    private DocumentStructure structure;
    private List<SummaryItem> items;
    private ConceptTree conceptTree;
    private ConceptTree namedEntityTree;
    private String relatedFactsQuery;
    private ConceptTree relatedFactsTree;

    public SummarizeMultipleDocumentResult() {
        //required for Jackson JSON Processor
    }

    /**
     * Get information about the documents
     *
     * @return Information about the documents
     */
    public List<Document> getDocuments() {
        return documents;
    }

    /**
     * Set information about the documents
     *
     * @param documents Information about the documents
     */
    public void setDocuments(List<Document> documents) {
        this.documents = documents;
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

    /**
     * Get query for related facts extraction (most important concepts and document sentences related to the query)
     *
     * @return Query for related facts extraction
     */
    public String getRelatedFactsQuery() {
        return relatedFactsQuery;
    }

    /**
     * Set query for related facts extraction (most important concepts and document sentences related to the query)
     *
     * @param relatedFactsQuery Query for related facts extraction
     */
    public void setRelatedFactsQuery(String relatedFactsQuery) {
        this.relatedFactsQuery = relatedFactsQuery;
    }

    /**
     * Get related facts tree along with the facts about the extracted concepts
     *
     * @return Related facts tree
     */
    public ConceptTree getRelatedFactsTree() {
        return relatedFactsTree;
    }

    /**
     * Set related facts tree along with the facts about the extracted concepts
     *
     * @param relatedFactsTree Related facts tree
     */
    public void setRelatedFactsTree(ConceptTree relatedFactsTree) {
        this.relatedFactsTree = relatedFactsTree;
    }
}
