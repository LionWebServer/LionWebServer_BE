package lionwebserver.lionwebserver.question.controller.dto;

import lionwebserver.lionwebserver.answer.controller.dto.AnswerDTO;

import java.time.LocalDateTime;
import java.util.List;

public record QuestionDTO(
        Long questionId,
        String title,
        String content,
        LocalDateTime createdAt,
        String userName
) {
}
