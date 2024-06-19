package com.blogg.app.services;

import com.blogg.app.dao.User;
import com.blogg.app.dto.auth.request.LoginUser;
import com.blogg.app.dto.auth.request.RegisterUser;
import com.blogg.app.dto.auth.response.LoginResponse;
import com.blogg.app.exceptions.EmailAlreadyExistsException;
import com.blogg.app.exceptions.UserNotCreatedException;
import com.blogg.app.exceptions.UserNotExistsException;
import com.blogg.app.exceptions.UsernameAlreadyTakenException;
import com.blogg.app.repositories.UserRepository;
import com.blogg.app.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

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

    public LoginResponse loginUser(LoginUser request) throws UserNotExistsException {
        String email = request.getEmail();
        String password = request.getPassword();
        User user = userRepository.findByEmail(email);
        if(user == null){
            //TODO:: throw error email is invalid
            throw new UserNotExistsException("User with email " + email + " doesn't exists");
        }
        if(!password.equals(user.getPassword())){
            //TODO:: throw password invalid exception
        }

        // Generate token
        String token = jwtService.generateToken(user);

        LoginResponse response = new LoginResponse();
        response.setAccessToken(token);

        return response;
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
