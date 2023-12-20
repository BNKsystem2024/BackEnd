package com.bnksys.onemind.apis.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "Quiz")
@ToString
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "question", nullable = false, length = 200)
    private String question;

    @Column(name = "answer", nullable = false, length = 5)
    private String answer;

    @Column(name = "commentary", nullable = false, length = 2000)
    private String commentary;

    @Column(name = "stcd", nullable = false, length = 2, columnDefinition = "CHAR(2)")
    private String stcd;

    @Column(name = "url", nullable = true, length = 200)
    private String url;

    @Column(name = "level", nullable = false)
    private Integer level;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Solved_problem> solvedProblems = new ArrayList<>();

    private Quiz(String question, String answer, String commentary, Integer level) {
        this.question = question;
        this.answer = answer;
        this.commentary = commentary;
        this.stcd = "01"; // TODO: Check stcd
        this.level = level;
    }

    public static Quiz of(String question, String answer, String commentary, Integer level) {
        return new Quiz(question, answer, commentary, level);
    }

}
