package lionwebserver.lionwebserver.answer.repository;

import lionwebserver.lionwebserver.answer.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
}
