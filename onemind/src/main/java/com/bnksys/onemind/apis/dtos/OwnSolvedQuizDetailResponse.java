package com.bnksys.onemind.apis.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OwnSolvedQuizDetailResponse {

    private String question;

    private String answer;

    private String commentary;

    private Integer level;

}
