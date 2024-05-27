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

        if (title == null || content == null) {
            throw new QuestionException(QuestionErrorCode.INVALID_INPUT_VALUE);
        }

        User userRef = em.getReference(User.class, userId);
        if (userRef == null) {
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

    public List<QuestionListDTO> getQuestions() {
        List<Question> questions = questionRepository.findAll();
        List<QuestionListDTO> questionList = questions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return questionList;
    }

    private QuestionListDTO convertToDTO(Question question) {
        return new QuestionListDTO(question.getId(), question.getTitle(), question.getUser().getUserName(), question.getCreatedAt());
    }

    public QuestionDTO getQuestion(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionException(QuestionErrorCode.QUESTION_NOT_FOUND));

        return new QuestionDTO(question.getId(), question.getTitle(), question.getContent(), question.getCreatedAt(), question.getUser().getUserName());
    }
}
