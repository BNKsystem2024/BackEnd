package com.bnksys.onemind.apis.controllers;

import com.bnksys.onemind.apis.dtos.OwnBadgesResponse;
import com.bnksys.onemind.apis.dtos.OwnSolvedQuizDetailResponse;
import com.bnksys.onemind.apis.dtos.OwnSolvedQuizListResponse;
import com.bnksys.onemind.apis.services.OwnService;
import com.bnksys.onemind.supports.codes.ErrorCode;
import com.bnksys.onemind.supports.responses.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/own")
@RequiredArgsConstructor
public class OwnController {

    private final Integer id = 1;
    private final OwnService ownService;

    @GetMapping(value = "/badges")
    public ResponseEntity<OwnBadgesResponse> getOwnBadges() {

        OwnBadgesResponse ownBadgesResponse = ownService.getOwnBadges(id);

        return ApiResponseUtil.success(ownBadgesResponse);
    }

    @GetMapping(value = "/quizzes/solved")
    public ResponseEntity<?> getOwnSolvedQuizzes(
        @RequestParam(required = false, name = "page") Integer page,
        @RequestParam(required = false, name = "quiz_id") Long quizId

    ) {
        if (page != null) {
            OwnSolvedQuizListResponse ownSolvedQuizListResponse = ownService.getOwnSolvedQuizList(
                id, page);
            return ApiResponseUtil.success(ownSolvedQuizListResponse);
        } else if (quizId != null) {
            OwnSolvedQuizDetailResponse ownSolvedQuizDetailResponse = ownService.getOwnSolvedQuizDetail(
                id, quizId);
            return ApiResponseUtil.success(ownSolvedQuizDetailResponse);
        }

        return ApiResponseUtil.error(ErrorCode.BAD_REQUEST);
    }

    @GetMapping(value = "/quizzes/solved/infos")
    public ResponseEntity<Void> getOwnSolvedQuizInfos(
        @RequestParam(name = "user_id") Integer userId) {
        // TODO: 문제의 푼 문제 전체 개수 대비 정답 퍼센트, 오답 개수, 정답 개수
        // TODO: return Dto
        return ApiResponseUtil.success();
    }

}
