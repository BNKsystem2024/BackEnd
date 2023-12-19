package com.bnksys.onemind.apis.controllers;


import com.bnksys.onemind.apis.dtos.RpaCreateQuizRequest;
import com.bnksys.onemind.apis.dtos.RpaCreateQuizResponse;
import com.bnksys.onemind.apis.services.RpaService;
import com.bnksys.onemind.supports.responses.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rpa")
@RequiredArgsConstructor
public class RpaController {

    private final RpaService rpaService;

    @PostMapping("/quizzes")
    public ResponseEntity<RpaCreateQuizResponse> saveQuiz(
        @RequestBody RpaCreateQuizRequest request) {

        return ApiResponseUtil.success(
            new RpaCreateQuizResponse(rpaService.saveQuizFromGptAnswerProcedure(request)));
    }
}
