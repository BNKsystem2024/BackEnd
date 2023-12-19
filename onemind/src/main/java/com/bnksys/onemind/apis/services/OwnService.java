package com.bnksys.onemind.apis.services;

import com.bnksys.onemind.apis.dtos.OwnSolvedQuizDetailResponse;
import com.bnksys.onemind.apis.dtos.OwnSolvedQuizListResponse;
import com.bnksys.onemind.apis.dtos.OwnSolvedQuizListResponse.OwnSolvedQuiz;
import com.bnksys.onemind.apis.entities.Quiz;
import com.bnksys.onemind.apis.entities.Solved_problem;
import com.bnksys.onemind.apis.repositories.QuizRepository;
import com.bnksys.onemind.apis.repositories.SolvedProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnService {

    private final SolvedProblemRepository solvedProblemRepository;
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
        Quiz quiz = quizRepository.findById(solvedQuiz.getId())
                                  .get();
        return new OwnSolvedQuiz(quiz.getQuestion(), solvedQuiz.getSolvedDate(),
            solvedQuiz.getIsCorrect());
    }

    public OwnSolvedQuizDetailResponse getOwnSolvedQuizDetail(Integer id, Integer quizId) {
        return null;
    }
}
