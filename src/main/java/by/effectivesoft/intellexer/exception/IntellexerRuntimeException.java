package by.effectivesoft.intellexer.exception;

public class IntellexerRuntimeException extends RuntimeException {
    public IntellexerRuntimeException() {
        super();
    }

    public IntellexerRuntimeException(String message) {
        super(message);
    }

    public IntellexerRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IntellexerRuntimeException(Throwable cause) {
        super(cause);
    }
}
