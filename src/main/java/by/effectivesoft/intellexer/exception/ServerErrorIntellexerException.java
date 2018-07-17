package by.effectivesoft.intellexer.exception;

/**
 * Exception is thrown in case of 5XX response code (Server Error)
 */
public class ServerErrorIntellexerException extends HttpIntellexerException {
    public ServerErrorIntellexerException() {
        super();
    }

    public ServerErrorIntellexerException(String message) {
        super(message);
    }

    public ServerErrorIntellexerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerErrorIntellexerException(Throwable cause) {
        super(cause);
    }
}
