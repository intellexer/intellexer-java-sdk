package by.effectivesoft.intellexer.exception;

/**
 * Exception is thrown in case of 401 response code (Unauthorized)
 */
public class UnauthorizedIntellexerException extends HttpIntellexerException {
    public UnauthorizedIntellexerException() {
        super();
    }

    public UnauthorizedIntellexerException(String message) {
        super(message);
    }

    public UnauthorizedIntellexerException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedIntellexerException(Throwable cause) {
        super(cause);
    }
}
