package com.yassine.user_notification_post_linkedin.dtos;

import com.yassine.user_notification_post_linkedin.entities.UserTab;
import com.yassine.user_notification_post_linkedin.enums.ConnectionStatus;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionDto {
    private Long followerId;
    private Long followedId;
    private LocalDateTime creationDateTime;
    private LocalDateTime updateDateTime;
    private ConnectionStatus connectionStatus;
}
