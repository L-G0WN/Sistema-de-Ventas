package com.monagas.entities.login;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "security_questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SecurityQuestion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "question1", length = 255, nullable = true)
    private String question1;

    @Column(name = "answer1", length = 255, nullable = true)
    private String answer1;

    @Column(name = "question2", length = 255, nullable = true)
    private String question2;

    @Column(name = "answer2", length = 255, nullable = true)
    private String answer2;

    @Column(name = "question3", length = 255, nullable = true)
    private String question3;

    @Column(name = "answer3", length = 255, nullable = true)
    private String answer3;
}
