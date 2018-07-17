package by.effectivesoft.intellexer.model.linguistic;

import java.util.List;

public class Sentence {
    private Text text;
    private List<Token> tokens;
    private List<Relation> relations;

    public Sentence() {
        //required for Jackson JSON Processor
    }

    /**
     * Get text of the sentence with offset information
     *
     * @return Text of the sentence
     */
    public Text getText() {
        return text;
    }

    /**
     * Set text of the sentence with offset information
     *
     * @param text Text of the sentence
     */
    public void setText(Text text) {
        this.text = text;
    }

    /**
     * Get list of tokens extracted from sentences
     *
     * @return List of tokens
     */
    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * Set list of tokens extracted from sentences
     *
     * @param tokens List of tokens
     */
    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    /**
     * Get list of extracted subject-verb-object relations
     *
     * @return List of extracted relations
     */
    public List<Relation> getRelations() {
        return relations;
    }

    /**
     * Set list of extracted subject-verb-object relations
     *
     * @param relations List of extracted relations
     */
    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }
}
