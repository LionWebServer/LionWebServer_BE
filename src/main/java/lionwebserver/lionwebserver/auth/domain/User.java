package lionwebserver.lionwebserver.auth.domain;

import jakarta.persistence.*;
import lionwebserver.lionwebserver.answer.domain.Answer;
import lionwebserver.lionwebserver.question.domain.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name="users")
@Getter
@Setter
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;
}

