package com.example.library.service;

import com.example.library.dto.UserActivityDTO;
import com.example.library.entity.User;
import com.example.library.entity.UserActivity;
import com.example.library.mapper.UserActivityMapper;
import com.example.library.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserActivityServiceImpl implements UserActivityService {

    @Autowired
    private UserActivityRepository activityRepository;
    @Autowired
    private UserActivityMapper activityMapper;

    public void logActivity(User user, String action) {
        UserActivity activity = new UserActivity();
        activity.setUser(user);
        activity.setAction(action);
        activity.setTime(LocalDateTime.now());
        activityRepository.save(activity);
    }
    @Override
    public List<UserActivityDTO> findAll() {
        return activityRepository.findAll().stream().map(a-> activityMapper.toDto(a)).collect(Collectors.toList());
    }

    @Override
    public List<UserActivityDTO> findAllByUserId(Long userId) {

        return activityRepository.findAllByUserId(userId).stream().map(a-> activityMapper.toDto(a)).collect(Collectors.toList());
    }
}
