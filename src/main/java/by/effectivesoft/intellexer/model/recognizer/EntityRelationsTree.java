package by.effectivesoft.intellexer.model.recognizer;

import java.util.List;

public class EntityRelationsTree {
    private List<EntityRelationsTree> children;
    private Integer count;
    private String dependency;
    private String entityText;
    private List<Integer> sentenceIds;
    private String text;
    private Integer type;

    public EntityRelationsTree() {
        //required for Jackson JSON Processor
    }

    /**
     * Get tree children
     *
     * @return Tree children
     */
    public List<EntityRelationsTree> getChildren() {
        return children;
    }

    /**
     * Set tree children
     *
     * @param children Tree children
     */
    public void setChildren(List<EntityRelationsTree> children) {
        this.children = children;
    }

    /**
     * Get entity count
     *
     * @return Count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Set entity count
     *
     * @param count Count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * Get entity dependency
     *
     * @return Dependency
     */
    public String getDependency() {
        return dependency;
    }

    /**
     * Set entity dependency
     *
     * @param dependency Dependency
     */
    public void setDependency(String dependency) {
        this.dependency = dependency;
    }

    /**
     * Get entity text
     *
     * @return Entity text
     */
    public String getEntityText() {
        return entityText;
    }

    /**
     * Set entity text
     *
     * @param entityText Entity text
     */
    public void setEntityText(String entityText) {
        this.entityText = entityText;
    }

    /**
     * Get an array of sentence identifiers
     *
     * @return Array of sentence identifiers
     */
    public List<Integer> getSentenceIds() {
        return sentenceIds;
    }

    /**
     * Set an array of sentence identifiers
     *
     * @param sentenceIds Array of sentence identifiers
     */
    public void setSentenceIds(List<Integer> sentenceIds) {
        this.sentenceIds = sentenceIds;
    }

    /**
     * Get entity text along with the entity type identifier
     *
     * @return Text with the entity type identifier
     */
    public String getText() {
        return text;
    }

    /**
     * Set entity text along with the entity type identifier
     *
     * @param text Text with the entity type identifier
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get entity type.
     * Possible values:
     * <br>0 – Unknown,
     * <br>1 – Person,
     * <br>2 – Organization,
     * <br>3 – Location,
     * <br>4 – Title,
     * <br>5 – Position,
     * <br>6 – Age,
     * <br>7 – Date,
     * <br>8 – Duration,
     * <br>9 – Nationality,
     * <br>10 – Event,
     * <br>11 – Url,
     * <br>12 – MiscellaneousLocation
     *
     * @return Entity type
     */
    public Integer getType() {
        return type;
    }

    /**
     * Set entity type.
     * Possible values:
     * <br>0 – Unknown,
     * <br>1 – Person,
     * <br>2 – Organization,
     * <br>3 – Location,
     * <br>4 – Title,
     * <br>5 – Position,
     * <br>6 – Age,
     * <br>7 – Date,
     * <br>8 – Duration,
     * <br>9 – Nationality,
     * <br>10 – Event,
     * <br>11 – Url,
     * <br>12 – MiscellaneousLocation
     *
     * @param type Entity type
     */
    public void setType(Integer type) {
        this.type = type;
    }
}
