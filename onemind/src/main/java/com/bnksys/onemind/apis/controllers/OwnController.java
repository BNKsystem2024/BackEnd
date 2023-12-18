package com.bnksys.onemind.apis.controllers;

import com.bnksys.onemind.supports.responses.ApiResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/own")
public class OwnController {

    @GetMapping(value = "/badges")
    public ResponseEntity<Void> getOwnBadges(@RequestParam(name = "user_id") Integer userId) {

        // TODO: return Dto
        return ApiResponseUtil.success();
    }

    @GetMapping(value = "/quizzes/solved")
    public ResponseEntity<Void> getOwnSolvedQuizzes(@RequestParam(name = "user_id") Integer userId,
        @RequestParam(required = false, name = "page") Integer page,
        @RequestParam(required = false, name = "quiz_id") Integer quizId

    ) {

        // TODO: return Dto
        return ApiResponseUtil.success();
    }

    @GetMapping(value = "/quizzes/solved/infos")
    public ResponseEntity<Void> getOwnSolvedQuizInfos(
        @RequestParam(name = "user_id") Integer userId) {
        // TODO: 문제의 푼 문제 전체 개수 대비 정답 퍼센트, 오답 개수, 정답 개수
        // TODO: return Dto
        return ApiResponseUtil.success();
    }

}
