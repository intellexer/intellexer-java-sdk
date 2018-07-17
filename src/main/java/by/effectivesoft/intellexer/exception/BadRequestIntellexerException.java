package by.effectivesoft.intellexer.exception;

/**
 * Exception is thrown in case of 400 response code (Bad Request)
 */
public class BadRequestIntellexerException extends HttpIntellexerException {
    public BadRequestIntellexerException() {
        super();
    }

    public BadRequestIntellexerException(String message) {
        super(message);
    }

    public BadRequestIntellexerException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestIntellexerException(Throwable cause) {
        super(cause);
    }
}
