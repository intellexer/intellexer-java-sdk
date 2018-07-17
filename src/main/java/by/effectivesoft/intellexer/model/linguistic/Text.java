package by.effectivesoft.intellexer.model.linguistic;

public class Text {
    private String content;
    private Integer beginOffset;
    private Integer endOffset;

    public Text() {
        //required for Jackson JSON Processor
    }

    /**
     * Get token plain text
     *
     * @return Token plain text
     */
    public String getContent() {
        return content;
    }

    /**
     * Set token plain text
     *
     * @param content Token plain text
     */
    public void setContext(String content) {
        this.content = content;
    }

    /**
     * Get start token offset in the source sentence
     *
     * @return Token offset
     */
    public Integer getBeginOffset() {
        return beginOffset;
    }

    /**
     * Set start token offset in the source sentence
     *
     * @param beginOffset Token offset
     */
    public void setBeginOffset(Integer beginOffset) {
        this.beginOffset = beginOffset;
    }

    /**
     * Get end token offset in the source sentence
     *
     * @return Token offset
     */
    public Integer getEndOffset() {
        return endOffset;
    }

    /**
     * Set end token offset in the source sentence
     *
     * @param endOffset Token offset
     */
    public void setEndOffset(Integer endOffset) {
        this.endOffset = endOffset;
    }
}