package lionwebserver.lionwebserver.question.repository;

import lionwebserver.lionwebserver.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<List<Question>> findAllByUserId(Long userId);
}
