package com.example.PoliticalPreparedness.services;

import com.example.PoliticalPreparedness.models.User;

public interface UserService {

    //Trivial Services
    public User addUser(User u);
    public User getUser(int id);
    public User updateUser(User change, int userId);
    public boolean deleteUser(int id);

}
