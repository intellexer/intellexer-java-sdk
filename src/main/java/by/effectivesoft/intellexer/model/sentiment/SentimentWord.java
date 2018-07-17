package by.effectivesoft.intellexer.model.sentiment;

public class SentimentWord {
    private String word;
    private Double weight;

    public SentimentWord(String word, Double weight) {
        this.word = word;
        this.weight = weight;
    }

    /**
     * Get word
     * @return Word
     */
    public String getWord() {
        return word;
    }

    /**
     * Set word
     * @param word Word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Get word's weight
     * @return Weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * Set word's weight
     * @param weight Weight
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
