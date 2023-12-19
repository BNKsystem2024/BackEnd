package com.bnksys.onemind.apis.controllers;

import com.bnksys.onemind.apis.dtos.QuizResponse;
import com.bnksys.onemind.apis.dtos.QuizSaveRequest;
import com.bnksys.onemind.apis.services.QuizService;
import com.bnksys.onemind.supports.codes.ErrorCode;
import com.bnksys.onemind.supports.responses.ApiResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/quizzes")
    public ResponseEntity<?> findRandom_Quiz() {
        try {
            QuizResponse quiz = quizService.findRandom_Quiz(1);
            if(quiz != null){
                return ApiResponseUtil.success(quiz);
            }else{
                return ApiResponseUtil.error(ErrorCode.BAD_REQUEST, "모든 퀴즈를 다 푸셨습니다.");
            }
        } catch (Exception e) {
            return ApiResponseUtil.error(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/quizzes")
    public ResponseEntity<?> saveSolvedQuiz(@RequestBody QuizSaveRequest quizSaveRequest) {
        try {
            boolean saved = quizService.saveSolvedQuiz(quizSaveRequest.getQuiz_id(), quizSaveRequest.isCorrect());
            if (saved) {
                return ResponseEntity.ok().build();
            } else {
                return ApiResponseUtil.error(ErrorCode.BAD_REQUEST, "저장된 퀴즈 문제가 없습니다.");
            }
        } catch (Exception e) {
            return ApiResponseUtil.error(ErrorCode.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다.");
        }
    }
}
