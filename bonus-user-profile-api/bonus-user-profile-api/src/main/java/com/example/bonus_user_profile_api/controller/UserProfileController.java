package com.example.bonus_user_profile_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bonus_user_profile_api.model.ApiResponse;
import com.example.bonus_user_profile_api.model.UserProfile;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {
 private List<UserProfile> users = new ArrayList<>();

    @PostMapping
    public ApiResponse<UserProfile> create(@RequestBody UserProfile user) {
        users.add(user);
        return new ApiResponse<>(true, "User profile created successfully", user);
    }

    @GetMapping
    public ApiResponse<List<UserProfile>> getAll() {
        return new ApiResponse<>(true, "Users retrieved", users);
    }

    @GetMapping("/username/{username}")
    public ApiResponse<List<UserProfile>> byUsername(@PathVariable String username) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) result.add(u);
        }
        return new ApiResponse<>(true, "Search result", result);
    }

    @PatchMapping("/{id}/activate")
    public ApiResponse<Void> activate(@PathVariable Long id) {
        for (UserProfile u : users) {
            if (u.getUserId().equals(id)) {
                u.setActive(true);
            }
        }
        return new ApiResponse<>(true, "User activated", null);
    }
}
