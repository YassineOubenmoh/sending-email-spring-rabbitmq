package com.yassine.user_notification_post_linkedin.controllers;

import com.yassine.user_notification_post_linkedin.dtos.UserDto;
import com.yassine.user_notification_post_linkedin.services.UserTabService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserTabController {

    private final UserTabService userTabService;

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {
        userTabService.addUser(userDto);
        return new ResponseEntity<>("User created Successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Set<UserDto>> getAllUsers() {
        Set<UserDto> users = userTabService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto user = userTabService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
