package com.bnksys.onemind.exceptions;

import com.bnksys.onemind.supports.codes.ErrorCode;
import java.io.Serializable;

public class CustomException extends RuntimeException implements Serializable {

    private final ErrorCode code;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode;
    }

    public ErrorCode getErrorCode() {
        return code;
    }
}
