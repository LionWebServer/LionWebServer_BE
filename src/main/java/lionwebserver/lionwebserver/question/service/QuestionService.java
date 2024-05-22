package lionwebserver.lionwebserver.question.service;

import jakarta.persistence.EntityManager;
import lionwebserver.lionwebserver.answer.controller.dto.AnswerDTO;
import lionwebserver.lionwebserver.answer.domain.Answer;
import lionwebserver.lionwebserver.auth.domain.User;
import lionwebserver.lionwebserver.question.controller.dto.*;
import lionwebserver.lionwebserver.question.domain.Question;
import lionwebserver.lionwebserver.question.exception.QuestionErrorCode;
import lionwebserver.lionwebserver.question.exception.QuestionException;
import lionwebserver.lionwebserver.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final EntityManager em;

    @Transactional
    public void createQuestion(QuestionCreateDTO questionCreateDTO, Long userId) {
        String title = questionCreateDTO.title();
        String content = questionCreateDTO.content();

        if(title == null || content == null){
            throw new QuestionException(QuestionErrorCode.INVALID_INPUT_VALUE);
        }

        User userRef = em.getReference(User.class, userId);
        if(userRef == null){
            throw new QuestionException(QuestionErrorCode.USER_NOT_FOUND);
        }
        Question question = Question.builder()
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .user(userRef)
                .build();
        questionRepository.save(question);
    }

    public QuestionPageResponse getQuestions(Integer page, Integer size) {
        if(page == null || size == null) {
            throw new QuestionException(QuestionErrorCode.INVALID_INPUT_VALUE);
        }
        if (page < 1 || size < 1) {
            throw new QuestionException(QuestionErrorCode.INVALID_INPUT_VALUE);
        }

        // PageRequest 생성 (페이지 인덱스는 0부터 시작하므로, page - 1을 수행)
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        // 데이터베이스에서 페이지네이션과 정렬을 적용하여 Question 리스트를 조회
        Page<Question> pageOfQuestions = questionRepository.findAll(pageable);

        // Entity 리스트를 DTO 리스트로 변환
        List<QuestionListDTO> questionDTOs = pageOfQuestions.getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return new QuestionPageResponse(questionDTOs, pageOfQuestions.getTotalPages(), pageOfQuestions.getTotalElements(), pageOfQuestions.getNumber() + 1, pageOfQuestions.getSize());
    }

    private QuestionListDTO convertToDTO(Question question) {
        // 필요에 따라 구현, 예를 들어:
        return new QuestionListDTO(question.getId(), question.getTitle(), question.getUser().getUserName(), question.getCreatedAt());
    }

    public QuestionDTO getQuestion(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionException(QuestionErrorCode.QUESTION_NOT_FOUND));

        return new QuestionDTO(question.getId(), question.getTitle(), question.getContent(), question.getCreatedAt(), question.getUser().getUserName());
    }
}
