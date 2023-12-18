package com.bnksys.onemind.apis.dtos;

import com.bnksys.onemind.apis.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class UserInfoResponse {
    private int id;
    private String dpnm;
    private String user_id;
    private String username;
    private String birthdate;

    public UserInfoResponse(User user) {
        this.id = user.getId();
        this.dpnm = user.getDept().getDpnm();
        this.user_id = user.getUserId();
        this.username = user.getUsername();
        this.birthdate = user.getBirthday();
    }
}
