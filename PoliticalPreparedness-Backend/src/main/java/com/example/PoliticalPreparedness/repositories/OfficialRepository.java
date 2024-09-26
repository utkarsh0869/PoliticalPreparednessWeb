package com.example.PoliticalPreparedness.repositories;

import com.example.PoliticalPreparedness.models.Official;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficialRepository extends JpaRepository<Official, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Official o WHERE o.user.id = :userId")
    void deleteOfficialByUserId(@Param("userId") int userId);
}

