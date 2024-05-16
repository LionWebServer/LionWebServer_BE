package lionwebserver.lionwebserver.answer.exception;

import lionwebserver.lionwebserver.exception.BaseException;

public class AnswerException extends BaseException {
    public AnswerException(AnswerErrorCode errorCode) {
        super(errorCode);
    }
}
