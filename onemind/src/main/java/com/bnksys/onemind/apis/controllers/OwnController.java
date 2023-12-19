package com.bnksys.onemind.apis.controllers;

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
    public ResponseEntity<Void> getOwnBadges(@RequestParam(name = "user_id") Integer userId) {

        // TODO: return Dto
        return ApiResponseUtil.success();
    }

    @GetMapping(value = "/quizzes/solved")
    public ResponseEntity<?> getOwnSolvedQuizzes(@RequestParam(name = "user_id") Integer userId,
        @RequestParam(required = false, name = "page") Integer page,
        @RequestParam(required = false, name = "quiz_id") Integer quizId

    ) {
        if (page != null) {
            OwnSolvedQuizListResponse ownSolvedQuizListResponse = ownService.getOwnSolvedQuizList(
                id, page);
            return ApiResponseUtil.success(ownSolvedQuizListResponse);
        } else if (quizId != null) {
            // TODO: Return detail Quiz Dto
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
