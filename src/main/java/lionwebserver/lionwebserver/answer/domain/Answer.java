package lionwebserver.lionwebserver.answer.domain;

import jakarta.persistence.*;
import lionwebserver.lionwebserver.auth.domain.User;
import lionwebserver.lionwebserver.question.domain.Question;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Answer {
    @Id @GeneratedValue
    @Column(name = "answer_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;
}
