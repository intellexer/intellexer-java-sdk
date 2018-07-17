package by.effectivesoft.intellexer.model.sentiment;

import by.effectivesoft.intellexer.util.SentimentTagParser;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class SentimentSentence {
    private String reviewId;
    private String text;
    private Double weight;

    public SentimentSentence() {
        //required for Jackson JSON Processor
    }

    /**
     * Get review identifier
     *
     * @return Review identifier
     */
    @JsonGetter("sid")
    public String getReviewId() {
        return reviewId;
    }

    /**
     * Set review identifier
     *
     * @param reviewId identifier
     */
    @JsonSetter("sid")
    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    /**
     * Get text of the sentence with the sentiment tags
     * <br>pos – positive words,
     * <br>neg – negative words,
     * <br>obj – sentiment objects
     *
     * @return Text of the sentence with the sentiment tags
     */
    public String getText() {
        return text;
    }

    /**
     * Set text of the sentence with the sentiment tags (
     * <br>pos – positive words,
     * <br>neg – negative words,
     * <br>obj – sentiment objects
     *
     * @param text Text of the sentence with the sentiment tags
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get sentiment weight of the sentence
     * <br> 0 - neutral information,
     * <br> &lt; 0 - negative information,
     * <br> &gt; 0 - positive information
     *
     * @return Sentiment weight of the sentence
     */
    @JsonGetter("w")
    public Double getWeight() {
        return weight;
    }

    /**
     * Set sentiment weight of the sentence
     * <br> 0 - neutral information,
     * <br> &lt; 0 - negative information,
     * <br> &gt; 0 - positive information
     *
     * @param weight Sentiment weight of the sentence
     */
    @JsonSetter("w")
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * Extract words with their weight from tags
     *
     * @param type Type of words to extract (positive, negative ir sentiment object)
     * @return List of extracted words with weights
     */
    public List<SentimentWord> getWordsWithWeight(SentimentWordType type) {
        return SentimentTagParser.extractWordsWithWeight(text, type);
    }

    /**
     * Extract words from tags
     *
     * @param type Type of words to extract (positive, negative ir sentiment object)
     * @return List of extracted words
     */
    public List<String> getWords(SentimentWordType type) {
        return SentimentTagParser.extractWords(text, type);
    }
}
