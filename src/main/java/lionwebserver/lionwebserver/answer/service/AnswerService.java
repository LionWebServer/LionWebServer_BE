package lionwebserver.lionwebserver.answer.service;

import jakarta.persistence.EntityManager;
import lionwebserver.lionwebserver.answer.controller.dto.AnswerCreateDTO;
import lionwebserver.lionwebserver.answer.controller.dto.AnswerDTO;
import lionwebserver.lionwebserver.answer.domain.Answer;
import lionwebserver.lionwebserver.answer.exception.AnswerErrorCode;
import lionwebserver.lionwebserver.answer.exception.AnswerException;
import lionwebserver.lionwebserver.answer.repository.AnswerRepository;
import lionwebserver.lionwebserver.auth.domain.User;
import lionwebserver.lionwebserver.question.domain.Question;
import lionwebserver.lionwebserver.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final EntityManager em;

    public void createAnswer(AnswerCreateDTO answerCreateDTO, Long userId) {
        String content = answerCreateDTO.content();

        if (content == null) {
            throw new AnswerException(AnswerErrorCode.EMPTY_INPUT_VALUE);
        }

        User userRef = em.getReference(User.class, userId);
        Question questionRef = em.getReference(Question.class, answerCreateDTO.questionId());

        if (userRef == null) {
            throw new AnswerException(AnswerErrorCode.USER_NOT_FOUND);
        }

        Answer answer = Answer.builder()
                .content(content)
                .user(userRef)
                .question(questionRef)
                .createdAt(LocalDateTime.now())
                .build();
        answerRepository.save(answer);
    }

    public List<AnswerDTO> getAnswers(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new AnswerException(AnswerErrorCode.QUESTION_NOT_FOUND));
        List<Answer> answers = question.getAnswers();
        return answers.stream()
                .map(answer -> new AnswerDTO(answer.getId(), answer.getContent()
                        , answer.getUser().getUserName(), LocalDateTime.now()))
                .toList();
    }
}
