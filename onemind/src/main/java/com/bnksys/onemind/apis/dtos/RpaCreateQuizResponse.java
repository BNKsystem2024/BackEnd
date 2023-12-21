package com.bnksys.onemind.apis.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RpaCreateQuizResponse {

    @JsonProperty("save_keyword_cnt")
    private Integer saveKeywordCnt;

    @JsonProperty("save_quiz_cnt")
    private Integer saveQuizCnt;

    @JsonProperty("save_related_keyword_cnt")
    private Integer saveRelatedKeywordCnt;

}
