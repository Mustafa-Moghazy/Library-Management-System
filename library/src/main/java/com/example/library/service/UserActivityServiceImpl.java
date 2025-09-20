package com.example.library.service;

import com.example.library.entity.User;
import com.example.library.entity.UserActivity;
import com.example.library.repository.UserActivityRepository;
import com.example.library.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserActivityServiceImpl implements UserActivityService {

    @Autowired
    private UserActivityRepository activityRepository;

    public void logActivity(User user, String action) {
        UserActivity activity = new UserActivity();
        activity.setUser(user);
        activity.setAction(action);
        activity.setTime(LocalDateTime.now());
        activityRepository.save(activity);
    }
    @Override
    public List<UserActivity> findAll() {
        return activityRepository.findAll();
    }

    @Override
    public List<UserActivity> findAllByUserId(Long userId) {
        return activityRepository.findAllByUserId(userId);
    }
}
