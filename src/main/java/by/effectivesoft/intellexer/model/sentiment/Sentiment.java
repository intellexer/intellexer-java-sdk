package by.effectivesoft.intellexer.model.sentiment;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Sentiment {
    private String id;
    private String author;
    private String datetime;
    private String title;
    private Integer weight;

    public Sentiment() {
        //required for Jackson JSON Processor
    }

    /**
     * Get review identifier
     *
     * @return Review identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Set review identifier
     *
     * @param id Review identifier
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get author of the review
     *
     * @return Author of the review
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set author of the review
     *
     * @param author Author of the review
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Get date and time when the review was written
     *
     * @return Date and time when the review was written
     */
    @JsonGetter("dt")
    public String getDatetime() {
        return datetime;
    }

    /**
     * Set date and time when the review was written
     *
     * @param datetime Date and time when the review was written
     */
    @JsonSetter("dt")
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    /**
     * Get review title
     *
     * @return Review title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set review title
     *
     * @param title Review title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get sentiment weight of the review
     *
     * @return Sentiment weight of the review
     */
    @JsonGetter("w")
    public Integer getWeight() {
        return weight;
    }

    /**
     * Set sentiment weight of the review
     *
     * @param weight Sentiment weight of the review
     */
    @JsonSetter("w")
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
