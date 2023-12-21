package com.bnksys.onemind.apis.dtos;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OwnSolvedQuizDetailResponse {

    List<OwnSolvedQuizDetail> solvedQuizDetailList;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class OwnSolvedQuizDetail {

        private String question;

        private String answer;

        private String commentary;

        private Integer level;
    }
}
