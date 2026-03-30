package com.yassine.user_notification_post_linkedin.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yassine.user_notification_post_linkedin.enums.ConnectionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference("user-follower_connection")
    @ManyToOne
    @JoinColumn(name = "user_follower_id")
    private UserTab userFollower;

    @JsonBackReference("user-followed_connection")
    @ManyToOne
    @JoinColumn(name = "user_followed_id")
    private UserTab userFollowed;

    @CreationTimestamp
    private LocalDateTime creationDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ConnectionStatus connectionStatus = ConnectionStatus.PENDING;

}
