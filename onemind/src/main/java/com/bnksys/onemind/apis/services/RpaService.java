package com.bnksys.onemind.apis.services;

import com.bnksys.onemind.apis.entities.Quiz;
import com.bnksys.onemind.apis.repositories.QuizRepository;
import com.bnksys.onemind.exceptions.CustomException;
import com.bnksys.onemind.supports.codes.ErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Transactional
    public Integer saveQuizFromGptAnswerProcedure(String gptAnswer) {

        System.out.println("-----입력된 값------");
        System.out.println(gptAnswer);

        // 1. Convert to Map
        List<Map<String, Object>> mapList = getMapFromObjectMapper(gptAnswer);

        // 2. Quiz 가져오기
        Map<String, Quiz> quizList = getQuizListFromMap(mapList);

        // 3. Keyword 가져오기
//        Map<String, List<String>> keywordList = getKeywordListFromMap(mapList);
        System.out.println("-----JSON 형태 추출 결과------");
        System.out.println(quizList);

//        quizRepository.saveAll(quizList);

        return quizList.size();
    }

    private void getKeywordListFromMap(List<Map<String, Object>> mapList) {
        Map<String, List<String>> mapForQuiz = new HashMap<>();

        for (Map<String, Object> map : mapList) {

            // Handling level type Error (String || Integer)
            Integer level = null;
            if (map.containsKey("level")) {
                Object levelObj = map.get("level");
                level = checkInvalidLevelType(levelObj);
            }

//            mapForQuiz.put(question, newQuiz);
        }

    }

    private Map<String, Quiz> getQuizListFromMap(List<Map<String, Object>> mapList) {

        Map<String, Quiz> mapForQuiz = new HashMap<>();

        for (Map<String, Object> map : mapList) {
            String question = map.containsKey("quiz") ? (String) map.get("quiz") : null;
            String answer = map.containsKey("answer") ? (String) map.get("answer") : null;
            String commentary =
                map.containsKey("commentary") ? (String) map.get("commentary") : null;

            // Handling filed name error
            if (commentary == null || commentary.isEmpty()) {
                commentary =
                    map.containsKey("commentery") ? (String) map.get("commentery") : null;
            }

            // Handling level type Error (String || Integer)
            Integer level = null;
            if (map.containsKey("level")) {
                Object levelObj = map.get("level");
                level = checkInvalidLevelType(levelObj);
            }

            Quiz newQuiz = Quiz.of(question, answer, commentary, level);
            mapForQuiz.put(question, newQuiz);
        }

        return mapForQuiz;
    }

    private List<Map<String, Object>> getMapFromObjectMapper(String gptAnswer) {

        Pattern pattern = Pattern.compile("\\{[^}]*\\}");
        Matcher matcher = pattern.matcher(gptAnswer);

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> mapList = new ArrayList<>();

        while (matcher.find()) {
            try {
                String json = matcher.group();
                Map<String, Object> map = objectMapper.readValue(json,
                    new TypeReference<Map<String, Object>>() {
                    });
                mapList.add(map);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return mapList;
    }


    private Integer checkInvalidLevelType(Object levelObj) {

        Integer level = null;

        if (levelObj instanceof Integer) {
            level = (Integer) levelObj;
        } else if (levelObj instanceof String) {
            try {
                level = Integer.parseInt((String) levelObj);
            } catch (NumberFormatException ex) {
                throw new CustomException(ErrorCode.INCORRECT_LEVEL_TYPE);
            }
        }
        return level;
    }
}
