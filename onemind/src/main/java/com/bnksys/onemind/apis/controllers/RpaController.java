package com.bnksys.onemind.apis.controllers;


import com.bnksys.onemind.apis.dtos.RpaCreateQuizResponse;
import com.bnksys.onemind.apis.services.RpaService;
import com.bnksys.onemind.supports.responses.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rpa")
@RequiredArgsConstructor
public class RpaController {

    private final RpaService rpaService;

    @PostMapping("/quizzes")
    public ResponseEntity<RpaCreateQuizResponse> saveQuiz(
        @RequestParam("gpt_answer") String gptAnswer) {

        return ApiResponseUtil.success(
            new RpaCreateQuizResponse(rpaService.saveQuizFromGptAnswerProcedure(gptAnswer)));
    }
}
