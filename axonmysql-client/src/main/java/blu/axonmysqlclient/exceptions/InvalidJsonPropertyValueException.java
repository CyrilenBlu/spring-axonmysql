package blu.axonmysqlclient.exceptions;

public class InvalidJsonPropertyValueException extends RuntimeException {
    public InvalidJsonPropertyValueException() {
    }

    public InvalidJsonPropertyValueException(String message) {
        super(message);
    }

    public InvalidJsonPropertyValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidJsonPropertyValueException(Throwable cause) {
        super(cause);
    }

    public InvalidJsonPropertyValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
