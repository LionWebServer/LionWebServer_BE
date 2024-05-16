package lionwebserver.lionwebserver.exception;

public interface ErrorCode {
    int getStatusCode();
    int getExceptionCode();
    String getMessage();
}

