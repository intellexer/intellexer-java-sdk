package by.effectivesoft.intellexer.model.spelling;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Candidate {
    private String text;
    private Double weight;

    public Candidate() {
        //required for Jackson JSON Processor
    }

    /**
     * Get correction text
     *
     * @return Correction text
     */
    @JsonGetter("t")
    public String getText() {
        return text;
    }

    /**
     * Set correction text
     *
     * @param text Correction text
     */
    @JsonSetter("t")
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get correction weight. Larger weight means greater relevance of the candidate correction
     *
     * @return Correction weight
     */
    @JsonGetter("w")
    public Double getWeight() {
        return weight;
    }

    /**
     * Set correction weight. Larger weight means greater relevance of the candidate correction
     *
     * @param weight Correction weight
     */
    @JsonSetter("w")
    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
