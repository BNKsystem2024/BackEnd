package com.bnksys.onemind.apis.services;

import com.bnksys.onemind.apis.dtos.QuizResponse;
import com.bnksys.onemind.apis.entities.User;
import com.bnksys.onemind.apis.repositories.QuizRepository;
import com.bnksys.onemind.apis.repositories.ReceivedBadgeRepository;
import com.bnksys.onemind.apis.repositories.SolvedProblemRepository;
import com.bnksys.onemind.apis.repositories.UserRepository;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    private final UserRepository userRepository;
    private final QuizRepository quizRepository;

    private final SolvedProblemRepository solvedProblemRepository;

    private final ReceivedBadgeRepository receivedBadgeRepository;

    public QuizService(UserRepository userRepository, QuizRepository quizRepository
        , SolvedProblemRepository solvedProblemRepository,
        ReceivedBadgeRepository receivedBadgeRepository) {
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
        this.solvedProblemRepository = solvedProblemRepository;
        this.receivedBadgeRepository = receivedBadgeRepository;
    }

    public QuizResponse findRandom_Quiz(int userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);

        String birthdayStr = user.get()
                                 .getBirthday();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birthday = LocalDate.parse(birthdayStr, formatter);
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthday, currentDate)
                        .getYears();

        int level = determineLevel(age); // 나이로 레벨 선정
        QuizResponse quiz = quizRepository.findRandom_Quiz(level, userId);
        if (quiz == null) {
            return null;
        } else {
            return quiz;
        }
    }

    public boolean saveSolvedQuiz(Long quizId, boolean isCorrect, Integer userId) {
        String solvedDate = LocalDate.now()
                                     .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int isCorrectValue = isCorrect ? 1 : 0;

        solvedProblemRepository.saveSolvedQuiz(1, quizId, solvedDate, isCorrectValue);
        if (true == isCorrect) {
            int correctCount = solvedProblemRepository.countCorrectAnswers(userId);
            if (1 == correctCount) {
                receivedBadgeRepository.saveReceived_Badge(1);
            } else if (10 == correctCount) {
                receivedBadgeRepository.saveReceived_Badge(2);
            } else if (20 == correctCount) {
                receivedBadgeRepository.saveReceived_Badge(3);
            } else if (30 == correctCount) {
                receivedBadgeRepository.saveReceived_Badge(4);
            }
        }

        return true;
    }

    public int determineLevel(int age) {
        if (age <= 19) {
            return 1;
        } else if (age <= 29) {
            return 2;
        } else {
            return 3;
        }
    }

}
