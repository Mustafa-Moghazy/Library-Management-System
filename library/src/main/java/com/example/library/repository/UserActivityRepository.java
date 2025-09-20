package com.example.library.repository;

import com.example.library.entity.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

    List<UserActivity> findAllByUserId(Long userId);

}
