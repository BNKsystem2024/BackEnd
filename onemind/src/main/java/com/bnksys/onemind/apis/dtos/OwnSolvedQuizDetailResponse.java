package com.bnksys.onemind.apis.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OwnSolvedQuizDetailResponse {

    private String question;

    private String answer;

    private String commentary;

}
