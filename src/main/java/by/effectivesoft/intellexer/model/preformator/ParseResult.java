package by.effectivesoft.intellexer.model.preformator;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class ParseResult {
    private String structure;
    private List<String> topics;
    private String language;
    private Integer languageId;
    private Integer inputSize;
    private Integer size;
    private String text;

    public ParseResult() {
        //required for Jackson JSON Processor
    }

    /**
     * Get document structure
     *
     * @return Document structure
     */
    public String getStructure() {
        return structure;
    }

    /**
     * Set document structure
     *
     * @param structure Document structure
     */
    public void setStructure(String structure) {
        this.structure = structure;
    }

    /**
     * Get list of of detected document topics
     *
     * @return List of of detected document topics
     */
    public List<String> getTopics() {
        return topics;
    }

    /**
     * Set list of of detected document topics
     *
     * @param topics List of of detected document topics
     */
    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    /**
     * Get document language
     *
     * @return Document language
     */
    @JsonGetter("lang")
    public String getLanguage() {
        return language;
    }

    /**
     * Set document language
     *
     * @param language Document language
     */
    @JsonSetter("lang")
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Get document language identifier
     *
     * @return Document language c
     */
    @JsonGetter("langId")
    public Integer getLanguageId() {
        return languageId;
    }

    /**
     * Set document language identifier
     *
     * @param languageId Document language identifier
     */
    @JsonSetter("langId")
    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    /**
     * Get size of the document before processing
     *
     * @return Size of the document before processing
     */
    public Integer getInputSize() {
        return inputSize;
    }

    /**
     * Set size of the document before processing
     *
     * @param inputSize Size of the document before processing
     */
    public void setInputSize(Integer inputSize) {
        this.inputSize = inputSize;
    }

    /**
     * Get size of extracted plain text
     *
     * @return Size of extracted plain text
     */
    public Integer getSize() {
        return size;
    }

    /**
     * Set size of extracted plain text
     *
     * @param size Size of extracted plain text
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * Get plain text from the input document
     *
     * @return Plain text from the input document
     */
    public String getText() {
        return text;
    }

    /**
     * Set plain text from the input document
     *
     * @param text Plain text from the input document
     */
    public void setText(String text) {
        this.text = text;
    }
}
