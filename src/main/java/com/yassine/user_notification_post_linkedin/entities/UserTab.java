package com.yassine.user_notification_post_linkedin.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String bio;

    @JsonManagedReference("user-follower_connection")
    @OneToMany(mappedBy = "userFollower")
    private Set<Connection> listFollowing;

    @JsonManagedReference("user-followed_connection")
    @OneToMany(mappedBy = "userFollowed")
    private Set<Connection> listFollowed;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
