package lionwebserver.lionwebserver.answer.controller.dto;

import java.time.LocalDateTime;

public record AnswerDTO(
        Long answerId,
        String content,
        String userName,
        LocalDateTime createdAt
) {
}
