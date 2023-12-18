package com.bnksys.onemind.apis.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OwnSolvedQuizInfosResponse {

    @JsonProperty("total_cnt")
    private Integer totalCnt;

    @JsonProperty("correct_cnt")
    private Integer correctCnt;

    @JsonProperty("incorrect_cnt")
    private Integer incorrectCnt;

    @JsonProperty("correct_rate")
    private Integer correctRate;
}
