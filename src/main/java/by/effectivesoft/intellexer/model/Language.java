package by.effectivesoft.intellexer.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Language {
    private String languageName;
    private String encoding;
    private Double weight;

    public Language() {
        //required for Jackson JSON Processor
    }

    /**
     * Get document language
     *
     * @return Document language
     */
    @JsonGetter("language")
    public String getLanguageName() {
        return languageName;
    }

    /**
     * Set document language
     *
     * @param languageName Document language
     */
    @JsonSetter("language")
    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    /**
     * Get document encoding
     *
     * @return Document encoding
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * Set document encoding
     *
     * @param encoding Document encoding
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * Get language weight. Larger weight means greater  relevance of the detected language
     *
     * @return Language weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * Set language weight. Larger weight means greater  relevance of the detected language
     *
     * @param weight Language weight
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
