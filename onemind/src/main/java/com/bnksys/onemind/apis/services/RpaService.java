package com.bnksys.onemind.apis.services;

import com.bnksys.onemind.apis.dtos.RpaCreateQuizRequest;
import com.bnksys.onemind.apis.entities.Quiz;
import com.bnksys.onemind.apis.repositories.QuizRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RpaService {

    private final QuizRepository quizRepository;

    public Integer saveQuizFromGptAnswerProcedure(RpaCreateQuizRequest request) {

        String gptAnswer = request.getGptAnswer();
        List<Quiz> quizList = getQuizList(gptAnswer);
        quizRepository.saveAll(quizList);

        return quizList.size();
    }


    public List<Quiz> getQuizList(String gptAnswer) {
        Pattern pattern = Pattern.compile("\\{[^}]*\\}");
        Matcher matcher = pattern.matcher(gptAnswer);

        ObjectMapper objectMapper = new ObjectMapper();

        List<Quiz> quizList = new LinkedList<>();
        while (matcher.find()) {
            try {
                // 추출한 JSON 문자열
                String json = matcher.group();

                // JSON을 Map 객체로 변환
                Map<String, Object> map = objectMapper.readValue(json, Map.class);

                // 필드 추출
                String question = (String) map.get("quiz");
                String answer = (String) map.get("answer");
                String commentary = (String) map.get("commentary");
                Integer level = (Integer) map.get("level");

                Quiz newQuiz = Quiz.of(question, answer, commentary, level);
                quizList.add(newQuiz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return quizList;
    }
}
