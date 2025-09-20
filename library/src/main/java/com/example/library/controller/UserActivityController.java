package com.example.library.controller;

import com.example.library.dto.UserActivityDTO;
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
    @GetMapping
    public ResponseEntity<List<UserActivityDTO>> getAllActivities() {
        List<UserActivityDTO> activities = userActivityService.findAll();
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserActivityDTO>> getUserActivities(@PathVariable Long userId) {
        List<UserActivityDTO> activities = userActivityService.findAllByUserId(userId);
        return ResponseEntity.ok(activities);
    }
}
