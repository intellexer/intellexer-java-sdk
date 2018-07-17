package by.effectivesoft.intellexer.model.linguistic;

public class Token {
    private Text text;
    private String partOfSpeechTag;
    private String lemma;

    public Token() {
        //required for Jackson JSON Processor
    }

    /**
     * Get text of the token with offset information
     *
     * @return Text of the token with offset information
     */
    public Text getText() {
        return text;
    }

    /**
     * Set text of the token with offset information
     *
     * @param text Text of the token with offset information
     */
    public void setText(Text text) {
        this.text = text;
    }

    /**
     * Get token part of speech tag (the tagset is a generalized version of the Lancaster-Oslo/Bergen (LOB)
     * tagset and consist of 37 tags)
     *
     * @return Token part of speech tag
     */
    public String getPartOfSpeechTag() {
        return partOfSpeechTag;
    }

    /**
     * Set token part of speech tag
     *
     * @param partOfSpeechTag Token part of speech tag
     */
    public void setPartOfSpeechTag(String partOfSpeechTag) {
        this.partOfSpeechTag = partOfSpeechTag;
    }

    /**
     * Get initial token form
     *
     * @return Initial token form
     */
    public String getLemma() {
        return lemma;
    }

    public void setLemma(String lemma) {
        this.lemma = lemma;
    }
}