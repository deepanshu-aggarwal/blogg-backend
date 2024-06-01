package com.blogg.app.services;

import com.blogg.app.dao.User;
import com.blogg.app.dto.auth.request.RegisterUser;
import com.blogg.app.exceptions.EmailAlreadyExistsException;
import com.blogg.app.exceptions.UserNotCreatedException;
import com.blogg.app.exceptions.UsernameAlreadyTakenException;
import com.blogg.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(RegisterUser request) throws UserNotCreatedException, UsernameAlreadyTakenException, EmailAlreadyExistsException {
        User userModel = createUserFromRequest(request);

        User user = userRepository.findByUsername(userModel.getUsername());
        if (user != null) {
            throw new UsernameAlreadyTakenException("Username has been already taken.");
        }

        user = userRepository.findByEmail(userModel.getEmail());
        if (user != null) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        try {
            userRepository.save(userModel);
        } catch (Exception e) {
            throw new UserNotCreatedException("Cannot register user, try again!");
        }
    }

    private User createUserFromRequest(RegisterUser request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        return user;
    }
}
