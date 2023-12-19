package com.bnksys.onemind.apis.controllers;


import com.bnksys.onemind.apis.dtos.RpaCreateQuizRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rpa")
public class RpaController {

    @PostMapping("/quiz")
    public ResponseEntity<?> analyzeArticles(@RequestBody RpaCreateQuizRequest request) {

        return null;
    }
}
