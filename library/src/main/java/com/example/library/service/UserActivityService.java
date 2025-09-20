package com.example.library.service;

import com.example.library.entity.User;
import com.example.library.entity.UserActivity;
import java.util.List;

public interface UserActivityService {
    void logActivity(User user, String action);
    List<UserActivity> findAll();

    List<UserActivity> findAllByUserId(Long userId);
}
