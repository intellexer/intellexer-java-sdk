package by.effectivesoft.intellexer.model.sentiment;

public class Review {
    private String id;
    private String text;

    public Review(String id, String text) {
        this.id = id;
        this.text = text;
    }

    /**
     * Get review id
     *
     * @return Review id
     */
    public String getId() {
        return id;
    }

    /**
     * Set review id
     *
     * @param id Review id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get review text
     *
     * @return Review text
     */
    public String getText() {
        return text;
    }

    /**
     * Set review text
     *
     * @param text Review text
     */
    public void setText(String text) {
        this.text = text;
    }
}
