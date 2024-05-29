package lionwebserver.lionwebserver.question.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lionwebserver.lionwebserver.auth.support.AuthUser;
import lionwebserver.lionwebserver.question.controller.dto.*;
import lionwebserver.lionwebserver.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    @PostMapping("/api/question")
    public ResponseEntity<?> createQuestion(@RequestBody QuestionCreateDTO questionCreateDTO,
                                             @Parameter(hidden = true) @AuthUser Long userId) {
        questionService.createQuestion(questionCreateDTO, userId);
        return ResponseEntity.ok().body("질문이 등록되었습니다.");
    }

    @GetMapping("/api/question/{questionId}")
    public ResponseEntity<QuestionDTO> getQuestions(@PathVariable Long questionId) {
        return ResponseEntity.ok().body(questionService.getQuestion(questionId));
    }
    @GetMapping("/api/question-list")
    public ResponseEntity<List<QuestionListDTO>> getQuestions() {
        return ResponseEntity.ok().body(questionService.getQuestions());
    }
}
