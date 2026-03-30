package com.yassine.user_notification_post_linkedin.controllers;

import com.yassine.user_notification_post_linkedin.dtos.ConnectionDto;
import com.yassine.user_notification_post_linkedin.enums.ConnectionStatus;
import com.yassine.user_notification_post_linkedin.services.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/connection")
public class ConnectionController {


    private final ConnectionService connectionService;

    @PostMapping
    public ResponseEntity<String> sendFollowRequest(@RequestBody ConnectionDto connectionDto) {
        connectionService.sendFollowRequest(connectionDto);
        return new ResponseEntity<>("Follow request sent successfully!", HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> changeConnectionStatus(
            @PathVariable("id") Long connectionId) {

        connectionService.changeConnectionStatus(connectionId);
        return ResponseEntity.ok("Connection status updated successfully!");
    }
}
