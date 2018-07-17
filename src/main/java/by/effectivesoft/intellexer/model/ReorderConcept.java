package by.effectivesoft.intellexer.model;

import java.util.List;

public class ReorderConcept {
    private String term;
    private List<String> selection;

    public ReorderConcept() {
        //required for Jackson JSON Processor
    }

    /**
     * Get concept term
     *
     * @return Concept term
     */
    public String getTerm() {
        return term;
    }

    /**
     * Set concept term
     *
     * @param term Concept term
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * Get list of concept selections
     *
     * @return List of concept selections
     */
    public List<String> getSelection() {
        return selection;
    }

    /**
     * Set list of concept selections
     *
     * @param selection List of concept selections
     */
    public void setSelection(List<String> selection) {
        this.selection = selection;
    }
}
