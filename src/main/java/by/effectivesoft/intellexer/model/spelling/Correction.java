package by.effectivesoft.intellexer.model.spelling;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class Correction {
    private Integer length;
    private Integer sentenceIndex;
    private Integer errorOffset;
    private List<Candidate> correctionCandidates;

    public Correction() {
        //required for Jackson JSON Processor
    }

    /**
     * Get length of the candidate correction
     *
     * @return Length of the candidate correction
     */
    @JsonGetter("l")
    public Integer getLength() {
        return length;
    }

    /**
     * Set length of the candidate correction
     *
     * @param length Length of the candidate correction
     */
    @JsonSetter("l")
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * Get index of the sentence with a spelling error
     *
     * @return Index of the sentence with a spelling error
     */
    @JsonGetter("ndx")
    public Integer getSentenceIndex() {
        return sentenceIndex;
    }

    /**
     * Set index of the sentence with a spelling error
     *
     * @param sentenceIndex Index of the sentence with a spelling error
     */
    @JsonSetter("ndx")
    public void setSentenceIndex(Integer sentenceIndex) {
        this.sentenceIndex = sentenceIndex;
    }

    /**
     * Get the offset of the error
     *
     * @return Offset of the error
     */
    @JsonGetter("s")
    public Integer getErrorOffset() {
        return errorOffset;
    }

    /**
     * Set the offset of the error
     *
     * @param errorOffset Offset of the error
     */
    @JsonSetter("s")
    public void setErrorOffset(Integer errorOffset) {
        this.errorOffset = errorOffset;
    }

    /**
     * Get list of candidate corrections
     *
     * @return List of candidate corrections
     */
    @JsonGetter("v")
    public List<Candidate> getCorrectionCandidates() {
        return correctionCandidates;
    }

    /**
     * Set list of candidate corrections
     *
     * @param correctionCandidates List of candidate corrections
     */
    @JsonSetter("v")
    public void setCorrectionCandidates(List<Candidate> correctionCandidates) {
        this.correctionCandidates = correctionCandidates;
    }
}
