package lionwebserver.lionwebserver.question.controller.dto;

import java.time.LocalDateTime;

public record QuestionListDTO(
        Long questionId,
        String title,
        String userName,
        LocalDateTime createdAt
) {
}
