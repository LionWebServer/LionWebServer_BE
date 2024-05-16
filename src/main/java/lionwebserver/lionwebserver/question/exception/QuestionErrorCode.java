package lionwebserver.lionwebserver.question.exception;

import lionwebserver.lionwebserver.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum QuestionErrorCode implements ErrorCode {
    QUESTION_NOT_FOUND(404, 2000, "질문을 찾을 수 없습니다."),
    INVALID_INPUT_VALUE(400, 2001, "잘못된 입력 값입니다."),
    USER_NOT_FOUND(404, 2002, "사용자를 찾을 수 없습니다."),;

    private final int statusCode;
    private final int exceptionCode;
    private final String message;

    QuestionErrorCode(int statusCode, int exceptionCode, String message) {
        this.statusCode = statusCode;
        this.exceptionCode = exceptionCode;
        this.message = message;
    }
}
