package com.bnksys.onemind.apis.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RpaCreateQuizRequest {

    @JsonProperty("gpt_answer")
    private String gptAnswer;

}