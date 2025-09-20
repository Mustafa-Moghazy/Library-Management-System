package com.example.library.service;

import com.example.library.dto.UserActivityDTO;
import com.example.library.entity.User;
import com.example.library.entity.UserActivity;
import java.util.List;

public interface UserActivityService {
    void logActivity(User user, String action);
    List<UserActivityDTO> findAll();

    List<UserActivityDTO> findAllByUserId(Long userId);
}
