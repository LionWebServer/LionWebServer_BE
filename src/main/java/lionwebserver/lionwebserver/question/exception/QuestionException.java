package lionwebserver.lionwebserver.question.exception;

import lionwebserver.lionwebserver.exception.BaseException;

public class QuestionException extends BaseException {
    public QuestionException(QuestionErrorCode errorCode) {
        super(errorCode);
    }
}
