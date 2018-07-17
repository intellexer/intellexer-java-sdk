package by.effectivesoft.intellexer.model;

public class Document {
    private Integer id;
    private Integer size;
    private String title;
    private String url;
    private String error;
    private String sizeFormat;

    public Document() {
        //required for Jackson JSON Processor
    }

    /**
     * Get document identifier
     *
     * @return Document identifier
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set document identifier
     *
     * @param id Document identifier
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get document size in bytes
     *
     * @return Document size in bytes
     */
    public Integer getSize() {
        return size;
    }

    /**
     * Set document size in bytes
     *
     * @param size Document size in bytes
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * Get document title
     *
     * @return Document title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set document title
     *
     * @param title Document title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get source of the request
     *
     * @return Document source url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set source of the request
     *
     * @param url Document source url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get information about processing errors
     *
     * @return Information about errors
     */
    public String getError() {
        return error;
    }

    /**
     * Set information about processing errors
     *
     * @param error Information about errors
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Get formatted document size
     *
     * @return Formatted document size
     */
    public String getSizeFormat() {
        return sizeFormat;
    }

    /**
     * Set formatted document size
     *
     * @param sizeFormat Formatted document size
     */
    public void setSizeFormat(String sizeFormat) {
        this.sizeFormat = sizeFormat;
    }
}
