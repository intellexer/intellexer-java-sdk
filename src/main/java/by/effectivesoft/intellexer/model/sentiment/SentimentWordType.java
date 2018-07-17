package by.effectivesoft.intellexer.model.sentiment;

public enum  SentimentWordType {
    POSITIVE("pos"),
    NEGATIVE("neg"),
    SENTIMENT_OBJECT("obj");

    private String tagName;

    SentimentWordType(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }
}
