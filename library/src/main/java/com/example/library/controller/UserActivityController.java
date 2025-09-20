package com.example.library.controller;

import com.example.library.entity.UserActivity;
import com.example.library.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class UserActivityController {

    @Autowired
    private UserActivityService userActivityService;

    // 🔹 Get all activities
    @GetMapping
    public ResponseEntity<List<UserActivity>> getAllActivities() {
        List<UserActivity> activities = userActivityService.findAll();
        return ResponseEntity.ok(activities);
    }

    // 🔹 Get activities by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserActivity>> getUserActivities(@PathVariable Long userId) {
        List<UserActivity> activities = userActivityService.findAllByUserId(userId);
        return ResponseEntity.ok(activities);
    }
}
