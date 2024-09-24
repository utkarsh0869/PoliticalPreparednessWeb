package com.example.PoliticalPreparedness.repositories;

import com.example.PoliticalPreparedness.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
