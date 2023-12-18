package com.bnksys.onemind.apis.dtos;

import com.bnksys.onemind.apis.entities.Solved_problem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class RankersResponse {

        String username;
        String dpnm;
        String alias;

        public RankersResponse(Solved_problem solvedProblem){
            this.username = solvedProblem.getUser().getUsername();
            this.dpnm = solvedProblem.getUser().getDept().getDpnm();
        }

}
