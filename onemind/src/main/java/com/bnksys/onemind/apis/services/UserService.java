package com.bnksys.onemind.apis.services;

import com.bnksys.onemind.apis.dtos.UsernameAndBirthResponse;
import com.bnksys.onemind.apis.entities.User;
import com.bnksys.onemind.apis.repositories.UserRepository;
import com.bnksys.onemind.exceptions.CustomException;
import com.bnksys.onemind.supports.codes.ErrorCode;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User authenticate_user(String userId, String password, HttpSession session) {
        User user = userRepository.findByUserId(userId);
        if (user != null && user.getUserPassword()
                                .equals(password)) {
            session.setAttribute("userId", user.getId());
            return user;
        }
        return null; // 사용자가 없거나 비밀번호가 일치하지 않는 경우
    }

    public UsernameAndBirthResponse getUsername(Integer userId) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new CustomException(ErrorCode.BAD_REQUEST));

        Integer age = calculateAge(user.getBirthday());
        return new UsernameAndBirthResponse(user.getUsername(), age);
    }

    private Integer calculateAge(String birthStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birthday = LocalDate.parse(birthStr, formatter);
        LocalDate currentDate = LocalDate.now();
        
        return Period.between(birthday, currentDate)
                     .getYears();
    }
}