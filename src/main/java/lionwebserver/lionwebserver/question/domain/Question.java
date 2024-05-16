package lionwebserver.lionwebserver.question.domain;

import jakarta.persistence.*;
import lionwebserver.lionwebserver.answer.domain.Answer;
import lionwebserver.lionwebserver.auth.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    @Builder
    public Question(String title, String content, LocalDateTime createdAt, User user) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
    }
    protected Question() {
    }
}
