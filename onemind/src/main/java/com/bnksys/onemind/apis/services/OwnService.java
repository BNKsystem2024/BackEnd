package com.bnksys.onemind.apis.services;

import com.bnksys.onemind.apis.dtos.OwnBadgesResponse;
import com.bnksys.onemind.apis.dtos.OwnBadgesResponse.OwnBadge;
import com.bnksys.onemind.apis.dtos.OwnSolvedQuizDetailResponse;
import com.bnksys.onemind.apis.dtos.OwnSolvedQuizListResponse;
import com.bnksys.onemind.apis.dtos.OwnSolvedQuizListResponse.OwnSolvedQuiz;
import com.bnksys.onemind.apis.entities.Badge;
import com.bnksys.onemind.apis.entities.Quiz;
import com.bnksys.onemind.apis.entities.Received_badge;
import com.bnksys.onemind.apis.entities.Solved_problem;
import com.bnksys.onemind.apis.repositories.QuizRepository;
import com.bnksys.onemind.apis.repositories.ReceivedBadgeRepository;
import com.bnksys.onemind.apis.repositories.SolvedProblemRepository;
import com.bnksys.onemind.exceptions.CustomException;
import com.bnksys.onemind.supports.codes.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnService {

    private final SolvedProblemRepository solvedProblemRepository;
    private final ReceivedBadgeRepository receivedBadgeRepository;
    private final QuizRepository quizRepository;

    public OwnSolvedQuizListResponse getOwnSolvedQuizList(Integer id, Integer page) {

        Pageable pageable = PageRequest.of(page - 1, 5);

        return new OwnSolvedQuizListResponse(
            solvedProblemRepository.findByUserId(id, pageable)
                                   .stream()
                                   .map(solvedProblem -> getOwnSolvedQuiz(solvedProblem))
                                   .toList());
    }

    public OwnSolvedQuiz getOwnSolvedQuiz(Solved_problem solvedQuiz) {

        Quiz quiz = solvedQuiz.getQuiz();

        return new OwnSolvedQuiz(quiz.getQuestion(),
            solvedQuiz.getSolvedDate(), solvedQuiz.getIsCorrect(), quiz.getLevel());
    }

    public OwnSolvedQuizDetailResponse getOwnSolvedQuizDetail(Integer id, Long quizId) {

        Quiz quiz = solvedProblemRepository.findByUserIdAndQuizId(id, quizId)
                                           .orElseThrow(
                                               () -> new CustomException(ErrorCode.BAD_REQUEST))
                                           .getQuiz();

        return new OwnSolvedQuizDetailResponse(quiz.getQuestion(), quiz.getAnswer(),
            quiz.getCommentary(), quiz.getLevel());
    }

    public OwnBadgesResponse getOwnBadges(Integer userId) {

        return new OwnBadgesResponse(receivedBadgeRepository.findByUserId(userId)
                                                            .stream()
                                                            .map(receivedBadge -> getOwnBadge(
                                                                receivedBadge))
                                                            .toList());
    }

    private OwnBadge getOwnBadge(Received_badge receivedBadge) {

        Badge badge = receivedBadge.getBadge();

        return new OwnBadge(badge.getId(), badge.getBadgeName(), badge.getBadgeSub());
    }
}
