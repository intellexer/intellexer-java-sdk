package by.effectivesoft.intellexer.model.sentiment;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

@JsonIgnoreProperties({"f", "rs"})
public class SentimentOpinion {
    private List<SentimentOpinion> children;
    private String text;
    private Double weight;

    public SentimentOpinion() {
        //required for Jackson JSON Processor
    }

    /**
     * Get opinion children
     *
     * @return Opinion children
     */
    public List<SentimentOpinion> getChildren() {
        return children;
    }

    /**
     * Set opinion children
     *
     * @param children Opinion children
     */
    public void setChildren(List<SentimentOpinion> children) {
        this.children = children;
    }

    /**
     * Get text of the opinion object, opinion phrase or the title of an ontology category
     *
     * @return Text of the opinion object, opinion phrase or the title of an ontology category
     */
    @JsonGetter("t")
    public String getText() {
        return text;
    }

    /**
     * Set text of the opinion object, opinion phrase or the title of an ontology category
     *
     * @param text Text of the opinion object, opinion phrase or the title of an ontology category
     */
    @JsonSetter("t")
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get sentiment weight of the opinion
     * Negative or positive values are used for opinion phrases, zero values – for objects or ontology categories
     *
     * @return Sentiment weight of the opinion
     */
    @JsonGetter("w")
    public Double getWeight() {
        return weight;
    }

    /**
     * Set sentiment weight of the opinion
     * Negative or positive values are used for opinion phrases, zero values – for objects or ontology categories
     *
     * @param weight Sentiment weight of the opinion
     */
    @JsonSetter("w")
    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
