package by.effectivesoft.intellexer.model;

public class RestAPIError {
    private Integer error;
    private String message;

    public RestAPIError() {
        //required for Jackson JSON Processor
    }

    /**
     * Get error code
     *
     * @return Error code
     */
    public Integer getError() {
        return error;
    }

    /**
     * Set error code
     *
     * @param error Error code
     */
    public void setError(Integer error) {
        this.error = error;
    }

    /**
     * Get error message
     *
     * @return Error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set error message
     *
     * @param message Error message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
