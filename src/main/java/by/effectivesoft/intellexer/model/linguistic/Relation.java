package by.effectivesoft.intellexer.model.linguistic;

public class Relation {
    private String subject;
    private String verb;
    private String object;
    private String adverbialPhrase;

    public Relation() {
        //required for Jackson JSON Processor
    }

    /**
     * Get relation subject
     *
     * @return Subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Set relation subject
     *
     * @param subject Subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Get relation verb
     *
     * @return Verb
     */
    public String getVerb() {
        return verb;
    }

    /**
     * Set relation verb
     *
     * @param verb Verb
     */
    public void setVerb(String verb) {
        this.verb = verb;
    }

    /**
     * Get relation object
     *
     * @return Object
     */
    public String getObject() {
        return object;
    }

    /**
     * Set relation object
     *
     * @param object Object
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * Get relation adverbial phrase
     *
     * @return Adverbial phrase
     */
    public String getAdverbialPhrase() {
        return adverbialPhrase;
    }

    /**
     * Set relation adverbial phrase
     *
     * @param adverbialPhrase Adverbial phrase
     */
    public void setAdverbialPhrase(String adverbialPhrase) {
        this.adverbialPhrase = adverbialPhrase;
    }
}