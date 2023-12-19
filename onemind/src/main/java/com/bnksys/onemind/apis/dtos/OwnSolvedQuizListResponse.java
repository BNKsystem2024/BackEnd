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

    @JsonProperty("data")
    private List<OwnSolvedQuiz> ownSolvedQuizList;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class OwnSolvedQuiz {

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
