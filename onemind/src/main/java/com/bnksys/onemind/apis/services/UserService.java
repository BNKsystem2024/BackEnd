package com.bnksys.onemind.apis.services;

import com.bnksys.onemind.apis.entities.User;
import com.bnksys.onemind.apis.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
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
}
