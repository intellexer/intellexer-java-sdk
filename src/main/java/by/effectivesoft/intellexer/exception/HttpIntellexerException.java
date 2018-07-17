package by.effectivesoft.intellexer.exception;

/**
 * Exception is thrown in case of 4XX or 5XX HTTP response code
 */
public class HttpIntellexerException extends IntellexerException {
    public HttpIntellexerException() {
        super();
    }

    public HttpIntellexerException(String message) {
        super(message);
    }

    public HttpIntellexerException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpIntellexerException(Throwable cause) {
        super(cause);
    }

}
