package lionwebserver.lionwebserver.auth.exception;

import lionwebserver.lionwebserver.exception.BaseException;

public class AuthException extends BaseException {
    public AuthException(AuthErrorCode errorCode) {
        super(errorCode);
    }
}
