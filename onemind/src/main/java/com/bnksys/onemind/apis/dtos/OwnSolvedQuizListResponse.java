package com.bnksys.onemind.apis.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OwnSolvedQuizListResponse {


    private List<OwnSolvedQuiz> ownSolvedQuizList;

    @JsonProperty("total_cnt")
    private Integer totalCnt;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class OwnSolvedQuiz {

        @JsonProperty("quizId")
        private Long quizId;

        @JsonProperty("question")
        private String question;

        @JsonProperty("solved_date")
        private String solvedDate;

        @JsonProperty("is_correct")
        private Boolean isCorrect;

        @JsonProperty("level")
        private Integer level;
    }

}
