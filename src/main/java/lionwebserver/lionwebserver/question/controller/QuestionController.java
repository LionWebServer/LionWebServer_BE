package lionwebserver.lionwebserver.question.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lionwebserver.lionwebserver.auth.support.AuthUser;
import lionwebserver.lionwebserver.question.controller.dto.*;
import lionwebserver.lionwebserver.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    @PostMapping("/api/question")
    public ResponseEntity<?> createQuestion(QuestionCreateDTO questionCreateDTO,
                                             @Parameter(hidden = true) @AuthUser Long userId) {
        questionService.createQuestion(questionCreateDTO, userId);
        return ResponseEntity.ok().body("질문이 등록되었습니다.");
    }

    @GetMapping("/api/question")
    public ResponseEntity<QuestionDTO> getQuestions(Long questionId) {
        return ResponseEntity.ok().body(questionService.getQuestion(questionId));
    }
    @GetMapping("/api/question-list")
    public ResponseEntity<QuestionPageResponse> getQuestions(@RequestParam int page,
                                                             @RequestParam int size) {

        return ResponseEntity.ok().body(questionService.getQuestions(page, size));
    }
}
