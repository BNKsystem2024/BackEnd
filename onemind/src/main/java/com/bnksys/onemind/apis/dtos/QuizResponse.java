package com.bnksys.onemind.apis.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
//@AllArgsConstructor
public class QuizResponse {
    private Long quizid;
    private String question;
    private String answer;
    private String commentary;
    private int level;

    public QuizResponse(Long quizid, String question, String answer, String commentary, int level) {
        this.quizid = quizid;
        this.question = question;
        this.answer = answer;
        this.commentary = commentary;
        this.level = level;
    }
}
