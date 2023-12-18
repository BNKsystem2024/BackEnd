package com.bnksys.onemind.apis.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginRequest {

    private String user_id;

    private String user_password;
}
