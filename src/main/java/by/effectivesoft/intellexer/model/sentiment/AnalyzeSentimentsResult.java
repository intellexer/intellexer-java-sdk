package by.effectivesoft.intellexer.model.sentiment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties("sentimentsCount")
public class AnalyzeSentimentsResult {
    private String ontology;
    private List<SentimentSentence> sentences;
    private List<SentimentOpinion> opinions;
    private List<Sentiment> sentiments;

    public AnalyzeSentimentsResult() {
        //required for Jackson JSON Processor
    }

    /**
     * Get ontology used to group the results
     *
     * @return Ontology used to group the results
     */
    public String getOntology() {
        return ontology;
    }

    /**
     * Set ontology used to group the results
     *
     * @param ontology Ontology used to group the results
     */
    public void setOntology(String ontology) {
        this.ontology = ontology;
    }

    /**
     * Get array of processed sentences
     *
     * @return Array of processed sentences
     */
    public List<SentimentSentence> getSentences() {
        return sentences;
    }

    /**
     * Set array of processed sentences
     *
     * @param sentences Array of processed sentences
     */
    public void setSentences(List<SentimentSentence> sentences) {
        this.sentences = sentences;
    }

    /**
     * Get tree of categorized opinions (opinion objects with sentiment phrases)
     *
     * @return Tree of categorized opinions
     */
    public List<SentimentOpinion> getOpinions() {
        return opinions;
    }

    /**
     * Set tree of categorized opinions (opinion objects with sentiment phrases)
     *
     * @param opinions Tree of categorized opinions
     */
    public void setOpinions(List<SentimentOpinion> opinions) {
        this.opinions = opinions;
    }

    /**
     * Get additional information about the processed reviews
     *
     * @return Additional information about the processed reviews
     */
    public List<Sentiment> getSentiments() {
        return sentiments;
    }

    /**
     * Set additional information about the processed reviews
     *
     * @param sentiments Additional information about the processed reviews
     */
    public void setSentiments(List<Sentiment> sentiments) {
        this.sentiments = sentiments;
    }
}
