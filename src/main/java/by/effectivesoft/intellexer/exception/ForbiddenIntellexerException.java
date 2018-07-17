package by.effectivesoft.intellexer.exception;

/**
 * Exception is thrown in case of 403 response code (Forbidden)
 */
public class ForbiddenIntellexerException extends HttpIntellexerException {
    public ForbiddenIntellexerException() {
        super();
    }

    public ForbiddenIntellexerException(String message) {
        super(message);
    }

    public ForbiddenIntellexerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenIntellexerException(Throwable cause) {
        super(cause);
    }
}
