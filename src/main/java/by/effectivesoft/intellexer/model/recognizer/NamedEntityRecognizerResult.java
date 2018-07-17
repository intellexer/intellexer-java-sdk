package by.effectivesoft.intellexer.model.recognizer;

import by.effectivesoft.intellexer.model.Document;

import java.util.List;

public class NamedEntityRecognizerResult {
    private Document document;
    private List<NamedEntity> entities;
    private List<String> sentences;
    private EntityRelationsTree relationsTree;

    public NamedEntityRecognizerResult() {
        //required for Jackson JSON Processor
    }

    /**
     * Get information about the text
     *
     * @return Information about the text
     */
    public Document getDocument() {
        return document;
    }

    /**
     * Set information about the text
     *
     * @param document Information about the text
     */
    public void setDocument(Document document) {
        this.document = document;
    }

    /**
     * Get list of detected entities
     *
     * @return List of detected entities
     */
    public List<NamedEntity> getEntities() {
        return entities;
    }

    /**
     * Set list of detected entities
     *
     * @param entities List of detected entities
     */
    public void setEntities(List<NamedEntity> entities) {
        this.entities = entities;
    }

    /**
     * Get list of processed sentences
     *
     * @return List of processed sentences
     */
    public List<String> getSentences() {
        return sentences;
    }

    /**
     * Set list of processed sentences
     *
     * @param sentences List of processed sentences
     */
    public void setSentences(List<String> sentences) {
        this.sentences = sentences;
    }

    /**
     * Get tree of relations among the detected entities
     *
     * @return Tree of relations
     */
    public EntityRelationsTree getRelationsTree() {
        return relationsTree;
    }

    /**
     * Set tree of relations among the detected entities
     *
     * @param relationsTree Tree of relations
     */
    public void setRelationsTree(EntityRelationsTree relationsTree) {
        this.relationsTree = relationsTree;
    }
}
