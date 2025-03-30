package com.monagas.entities.login;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", length = 50, unique = true, nullable = false)
    private String username;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "firstname", length = 50, nullable = false)
    private String firstname;

    @Column(name = "lastname", length = 50, nullable = false)
    private String lastname;

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

    @Column(name = "account_type", nullable = false)
    private Integer accountType;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "is_firstime")
    private boolean isFirstime;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;
}
