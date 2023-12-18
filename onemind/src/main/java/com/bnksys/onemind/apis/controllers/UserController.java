package com.bnksys.onemind.apis.controllers;

import com.bnksys.onemind.apis.dtos.LoginRequest;
import com.bnksys.onemind.apis.dtos.RankersResponse;
import com.bnksys.onemind.apis.dtos.UserInfoResponse;
import com.bnksys.onemind.apis.entities.User;
import com.bnksys.onemind.apis.services.SolvedProblemService;
import com.bnksys.onemind.apis.services.UserService;
import com.bnksys.onemind.supports.codes.ErrorCode;
import com.bnksys.onemind.supports.responses.ApiResponseUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SolvedProblemService solvedProblemService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.authenticate_user(loginRequest.getUser_id(), loginRequest.getUser_password());
            if (user != null) {
                UserInfoResponse userInfoResponse = new UserInfoResponse(user);
                return ApiResponseUtil.success(userInfoResponse);
            } else {
                return ApiResponseUtil.error(ErrorCode.UNAUTHORIZED, "존재하는 유저가 없습니다.");
            }
        } catch (Exception e) {
            return ApiResponseUtil.error(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/members/rankers")
    public ResponseEntity<?> findRankers() {
        List<RankersResponse> rankers = solvedProblemService.findRankers();
        return ResponseEntity.ok(rankers);
    }
}
