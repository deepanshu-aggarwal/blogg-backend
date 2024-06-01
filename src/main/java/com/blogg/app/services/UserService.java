package com.blogg.app.services;

import com.blogg.app.dao.User;
import com.blogg.app.exceptions.UserNotExistsException;
import com.blogg.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(int id) throws UserNotExistsException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotExistsException("User with id " + id + " does not exists.");
        }

        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
