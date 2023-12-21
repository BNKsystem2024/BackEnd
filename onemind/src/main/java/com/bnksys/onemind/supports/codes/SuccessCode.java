package com.bnksys.onemind.supports.codes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum
SuccessCode {

    OK(200, "OK"),
    CREATED(201, "Resource Created Successfully"),
    ACCEPTED(202, "Request Accepted for Processing"),
    NO_CONTENT(204, "No Content"),
    RESET_CONTENT(205, "Reset Content"), // 요청은 성공적인데 클라이언트가 문서 보기를 초기화해야할 때
    PARTIAL_CONTENT(206, "Partial Content Provided"); // Client가 Range Header를 써서 일부 리소스만 요청시

    private final Integer status;
    private final String message;
}
