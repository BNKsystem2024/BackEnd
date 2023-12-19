package com.bnksys.onemind.apis.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RpaCreateQuizRequest {

    private String gptAnswer;
    
}
