package lionwebserver.lionwebserver.answer.exception;

import lionwebserver.lionwebserver.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum AnswerErrorCode implements ErrorCode {
    USER_NOT_FOUND(3000, 404, "사용자를 찾을 수 없습니다."),
    QUESTION_NOT_FOUND(3001, 404, "질문을 찾을 수 없습니다."),
    EMPTY_INPUT_VALUE(3002, 400, "빈 입력 값입니다.");


    private final int exceptionCode;
    private final int statusCode;
    private final String message;

    AnswerErrorCode(int exceptionCode, int statusCode, String message) {
        this.exceptionCode = exceptionCode;
        this.statusCode = statusCode;
        this.message = message;
    }
}
