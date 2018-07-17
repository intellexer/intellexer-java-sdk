package by.effectivesoft.intellexer.model.summarizer;

public class SummaryItem {
    private String text;
    private Integer rank;
    private Double weight;

    public SummaryItem() {
        //required for Jackson JSON Processor
    }

    /**
     * Get text of the summary item
     * @return Text of the summary item
     */
    public String getText() {
        return text;
    }

    /**
     * Set text of the summary item
     * @param text Text of the summary item
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get item rank. Larger rank means greater importance of the sentence
     * @return Item rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * Set item rank. Larger rank means greater importance of the sentence
     * @param rank Item rank
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    /**
     * Get item weight
     * @return Item weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * Set item weight
     * @param weight Item weight
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
