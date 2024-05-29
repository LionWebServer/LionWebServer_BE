package lionwebserver.lionwebserver.answer.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lionwebserver.lionwebserver.answer.controller.dto.AnswerCreateDTO;
import lionwebserver.lionwebserver.answer.controller.dto.AnswerDTO;
import lionwebserver.lionwebserver.answer.service.AnswerService;
import lionwebserver.lionwebserver.auth.support.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    @PostMapping("/api/answer")
    public ResponseEntity<?> createAnswer(@RequestBody AnswerCreateDTO answerCreateDTO,
                                          @Parameter(hidden = true) @AuthUser Long userId) {
        answerService.createAnswer(answerCreateDTO, 1L);
        return ResponseEntity.ok().body("답변이 등록되었습니다.");
    }

    @GetMapping("/api/answer-list/{questionId}")
    public ResponseEntity<List<AnswerDTO>> getAnswers(@PathVariable Long questionId) {
        return ResponseEntity.ok().body(answerService.getAnswers(questionId));
    }
}
