package by.effectivesoft.intellexer.model.comparator;

import by.effectivesoft.intellexer.model.Document;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CompareResult {
    private Double proximity;
    private Document firstDocument;
    private Document secondDocument;

    public CompareResult() {
        //required for Jackson JSON Processor
    }

    /**
     * Get proximity between documents. The proximity is calculated within the range of 0-1,
     * where «0» means "completely different texts" and «1» means "completely identical texts"
     *
     * @return Proximity between documents
     */
    public Double getProximity() {
        return proximity;
    }

    /**
     * Set proximity between documents
     *
     * @param proximity Proximity between documents
     */
    public void setProximity(Double proximity) {
        this.proximity = proximity;
    }

    /**
     * Get information about the first document
     *
     * @return Information about the first document
     */
    @JsonGetter("document1")
    public Document getFirstDocument() {
        return firstDocument;
    }

    /**
     * Set information about the first document
     *
     * @param firstDocument Document information
     */
    @JsonSetter("document1")
    public void setFirstDocument(Document firstDocument) {
        this.firstDocument = firstDocument;
    }

    /**
     * Get information about the second document
     *
     * @return Information about the second document
     */
    @JsonGetter("document2")
    public Document getSecondDocument() {
        return secondDocument;
    }

    /**
     * Set information about the second document
     *
     * @param secondDocument Document information
     */
    @JsonSetter("document2")
    public void setSecondDocument(Document secondDocument) {
        this.secondDocument = secondDocument;
    }
}
