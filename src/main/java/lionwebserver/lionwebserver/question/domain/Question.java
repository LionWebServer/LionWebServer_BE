package lionwebserver.lionwebserver.question.domain;

import jakarta.persistence.*;
import lionwebserver.lionwebserver.auth.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Question {

    @Id @GeneratedValue
    @Column(name = "question_id")
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
