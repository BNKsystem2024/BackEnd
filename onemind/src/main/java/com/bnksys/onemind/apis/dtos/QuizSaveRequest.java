package com.bnksys.onemind.apis.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
public class QuizSaveRequest {
    private Long quiz_id;

    @JsonProperty("is_correct")
    private boolean isCorrect;
}
