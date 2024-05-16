package lionwebserver.lionwebserver.question.controller.dto;

import java.util.List;

public record QuestionPageResponse(
        List<QuestionListDTO> questionList,
        int totalPage,
        long totalElements,
        int currentPage,
        int size
) {
}
