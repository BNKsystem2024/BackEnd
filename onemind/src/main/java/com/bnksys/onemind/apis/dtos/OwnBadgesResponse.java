package com.bnksys.onemind.apis.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OwnBadgesResponse {

    @JsonProperty("data")
    private List<OwnBadge> OwnBadgeList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class OwnBadge {

        @JsonProperty("badge_id")
        private Integer badgeId;

        @JsonProperty("badge_name")
        private String badgeName;

        @JsonProperty("badge_sub")
        private String badgeSub;
    }
}
