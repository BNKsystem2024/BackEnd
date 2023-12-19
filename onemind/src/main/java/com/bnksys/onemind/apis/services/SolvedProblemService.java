package com.bnksys.onemind.apis.services;

import com.bnksys.onemind.apis.dtos.RankersResponse;
import com.bnksys.onemind.apis.entities.Solved_problem;
import com.bnksys.onemind.apis.entities.User;
import com.bnksys.onemind.apis.repositories.SolvedProblemRepository;
import com.bnksys.onemind.apis.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolvedProblemService {

    @Autowired
    private UserRepository userRepository;
    private SolvedProblemRepository solvedProblemRepository;

    public SolvedProblemService(SolvedProblemRepository solvedProblemRepository) {
        this.solvedProblemRepository = solvedProblemRepository;
    }

    public List<RankersResponse> findRankers() {
        Optional<Integer> topCorrectUserId = solvedProblemRepository.findTopCorrectAnswerer();
        Optional<Integer> topIncorrectUserId = solvedProblemRepository.findTopIncorrectAnswerer();

        List<RankersResponse> rankers = new ArrayList<>();

        if (topCorrectUserId.isPresent()) {
            int userId = topCorrectUserId.get();
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                rankers.add(new RankersResponse(user.getUsername(), user.getDept().getDpnm(), "이달의 정답왕"));
            }
        }

        if (topIncorrectUserId.isPresent()) {
            int userId = topIncorrectUserId.get();
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                rankers.add(new RankersResponse(user.getUsername(), user.getDept().getDpnm(), "이달의 오답왕"));
            }
        }

        return rankers;
    }
}
