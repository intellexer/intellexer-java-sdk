package by.effectivesoft.intellexer.model.spelling;

import java.util.List;

public class SpellCheckResult {
    private Integer inputSize;
    private Integer sentencesCount;
    private List<String> processedSentences;
    private List<String> sourceSentences;
    private List<Correction> corrections;

    public SpellCheckResult() {
        //required for Jackson JSON Processor
    }

    /**
     * Get size of the document
     *
     * @return Size of the document
     */
    public Integer getInputSize() {
        return inputSize;
    }

    /**
     * Set size of the document
     *
     * @param inputSize Size of the document
     */
    public void setInputSize(Integer inputSize) {
        this.inputSize = inputSize;
    }

    /**
     * Get number of processed sentences
     *
     * @return Number of processed sentences
     */
    public Integer getSentencesCount() {
        return sentencesCount;
    }

    /**
     * Set number of processed sentences
     *
     * @param sentencesCount Number of processed sentences
     */
    public void setSentencesCount(Integer sentencesCount) {
        this.sentencesCount = sentencesCount;
    }

    /**
     * Get list of corrected sentences
     *
     * @return List of corrected sentences
     */
    public List<String> getProcessedSentences() {
        return processedSentences;
    }

    /**
     * Set list of corrected sentences
     *
     * @param processedSentences List of corrected sentences
     */
    public void setProcessedSentences(List<String> processedSentences) {
        this.processedSentences = processedSentences;
    }

    /**
     * Get list of source sentences
     *
     * @return List of source sentences
     */
    public List<String> getSourceSentences() {
        return sourceSentences;
    }

    /**
     * Set list of source sentences
     *
     * @param sourceSentences List of source sentences
     */
    public void setSourceSentences(List<String> sourceSentences) {
        this.sourceSentences = sourceSentences;
    }

    /**
     * Get list of candidate corrections
     *
     * @return List of candidate corrections
     */
    public List<Correction> getCorrections() {
        return corrections;
    }

    /**
     * Set list of candidate corrections
     *
     * @param corrections List of candidate corrections
     */
    public void setCorrections(List<Correction> corrections) {
        this.corrections = corrections;
    }
}
