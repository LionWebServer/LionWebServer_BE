package lionwebserver.lionwebserver.answer.controller.dto;

public record AnswerCreateDTO(
        Long questionId,
        String content
) {
}
