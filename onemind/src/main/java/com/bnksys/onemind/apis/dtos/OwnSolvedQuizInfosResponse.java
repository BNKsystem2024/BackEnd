package com.bnksys.onemind.apis.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OwnSolvedQuizInfosResponse {

    @JsonProperty("total_cnt")
    private Long totalCnt;

    @JsonProperty("correct_cnt")
    private Long correctCnt;

    @JsonProperty("incorrect_cnt")
    private Long incorrectCnt;

    @JsonProperty("correct_rate")
    private Long correctRate;
}
