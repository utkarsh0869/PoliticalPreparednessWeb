package com.example.PoliticalPreparedness.services;

import com.example.PoliticalPreparedness.exceptions.UserNotFoundException;
import com.example.PoliticalPreparedness.models.User;
import com.example.PoliticalPreparedness.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id)
            .orElseThrow(
                    () -> new UserNotFoundException("User with id " + id + " not found.")
            );
    }
}
