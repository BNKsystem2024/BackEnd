package com.bnksys.onemind.apis.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OwnBadgesResponse {


    @JsonProperty("badge_id")
    private Integer badgeId;

    @JsonProperty("badge_name")
    private String badgeName;

    @JsonProperty("badge_sub")
    private String badgeSub;
}
