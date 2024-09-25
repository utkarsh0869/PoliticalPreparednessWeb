package com.example.PoliticalPreparedness.services;

import com.example.PoliticalPreparedness.exceptions.DuplicateUserException;
import com.example.PoliticalPreparedness.exceptions.UserNotFoundException;
import com.example.PoliticalPreparedness.models.User;
import com.example.PoliticalPreparedness.repositories.UserRepository;
import com.example.PoliticalPreparedness.utils.LoginRequest;
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
    public User addUser(User u) {
        boolean userExists = userRepository.findByEmail(u.getEmail()).isPresent();

        if(userExists) {
            throw new DuplicateUserException("User with email " + u.getEmail() + " already exists.");
        }

        return userRepository.save(u);
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id)
            .orElseThrow(
                    () -> new UserNotFoundException("User with id " + id + " not found.")
            );
    }

    @Override
    public User updateUser(User change, int userId) {
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException("User with id " + id + " not found.");
        }

        userRepository.deleteById(id);

        // Returns true if user with the give id it's not found.
        return !userRepository.existsById(id);
    }

    public User login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User with email " + request.getEmail() + " not found."));

        if(!user.getPassword().equals(request.getPassword())) {
            throw new UserNotFoundException("Wrong password, try again!");
        }

        return user;
    }
}
