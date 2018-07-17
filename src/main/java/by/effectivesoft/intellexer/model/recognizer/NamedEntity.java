package by.effectivesoft.intellexer.model.recognizer;

import by.effectivesoft.intellexer.model.EntityType;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class NamedEntity {
    private List<Integer> sentenceIds;
    private EntityType type;
    private Integer numberOfWords;
    private String text;

    public NamedEntity() {
        //required for Jackson JSON Processor
    }


    /**
     * Get array of sentence identifiers containing extracted entities
     *
     * @return Array of sentence identifiers
     */
    public List<Integer> getSentenceIds() {
        return sentenceIds;
    }

    /**
     * Set array of sentence identifiers containing extracted entities
     *
     * @param sentenceIds Array of sentence identifiers
     */
    public void setSentenceIds(List<Integer> sentenceIds) {
        this.sentenceIds = sentenceIds;
    }

    /**
     * Get entity type.
     * Possible values:
     * <br>0 – Unknown,
     * <br>1 – Person,
     * <br>2 – Organization,
     * <br>3 – Location,
     * <br>4 – Title,
     * <br>5 – Position,
     * <br>6 – Age,
     * <br>7 – Date,
     * <br>8 – Duration,
     * <br>9 – Nationality,
     * <br>10 – Event,
     * <br>11 – Url,
     * <br>12 – MiscellaneousLocation
     *
     * @return Entity type
     */
    public EntityType getType() {
        return type;
    }

    /**
     * Set entity type.
     * Possible values:
     * <br>0 – Unknown,
     * <br>1 – Person,
     * <br>2 – Organization,
     * <br>3 – Location,
     * <br>4 – Title,
     * <br>5 – Position,
     * <br>6 – Age,
     * <br>7 – Date,
     * <br>8 – Duration,
     * <br>9 – Nationality,
     * <br>10 – Event,
     * <br>11 – Url,
     * <br>12 – MiscellaneousLocation
     *
     * @param type Entity type
     */
    public void setType(EntityType type) {
        this.type = type;
    }

    /**
     * Get number of words in the entity
     *
     * @return Number of words in the entity
     */
    @JsonGetter("wc")
    public Integer getNumberOfWords() {
        return numberOfWords;
    }

    /**
     * Set number of words in the entity
     *
     * @param numberOfWords Number of words in the entity
     */
    @JsonSetter("wc")
    public void setNumberOfWords(Integer numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    /**
     * Get entity text
     *
     * @return Entity text
     */
    public String getText() {
        return text;
    }

    /**
     * Set entity text
     *
     * @param text Entity text
     */
    public void setText(String text) {
        this.text = text;
    }
}
