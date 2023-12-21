package com.bnksys.onemind.apis.services;

import com.bnksys.onemind.apis.entities.Quiz;
import com.bnksys.onemind.apis.repositories.QuizRepository;
import com.bnksys.onemind.exceptions.CustomException;
import com.bnksys.onemind.supports.codes.ErrorCode;
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

    public Integer saveQuizFromGptAnswerProcedure(String gptAnswer) {

        System.out.println("-----입력된 값------");
        System.out.println(gptAnswer);
        List<Quiz> quizList = getQuizList(gptAnswer);

        System.out.println("-----JSON 형태 추출 결과------");
        System.out.println(quizList);

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

                // JSON convert to Map
                Map<String, Object> map = objectMapper.readValue(json, Map.class);

                // extract field
                String question = map.containsKey("quiz") ? (String) map.get("quiz") : null;
                String answer = map.containsKey("answer") ? (String) map.get("answer") : null;
                String commentary =
                    map.containsKey("commentary") ? (String) map.get("commentary") : null;

                // filed name error handling
                if (commentary == null || commentary.isEmpty()) {
                    commentary =
                        map.containsKey("commentery") ? (String) map.get("commentery") : null;
                }

                // level이 String값으로 들어왔을 경우 예외처리하기
                Integer level = null;
                if (map.containsKey("level")) {
                    Object levelObj = map.get("level");
                    if (levelObj instanceof Integer) {
                        level = (Integer) levelObj;
                    } else if (levelObj instanceof String) {
                        try {
                            level = Integer.parseInt((String) levelObj);
                        } catch (NumberFormatException ex) {
                            throw new CustomException(ErrorCode.INCORRECT_LEVEL_TYPE);
                        }
                    }
                }

                Quiz newQuiz = Quiz.of(question, answer, commentary, level);
                quizList.add(newQuiz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return quizList;
    }


    private boolean checkInvalidLevelType(Object levelObj) {

        return false;
    }
}
