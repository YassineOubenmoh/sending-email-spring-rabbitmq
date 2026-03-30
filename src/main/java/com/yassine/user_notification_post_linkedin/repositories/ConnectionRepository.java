package com.yassine.user_notification_post_linkedin.repositories;

import com.yassine.user_notification_post_linkedin.entities.Connection;
import com.yassine.user_notification_post_linkedin.entities.UserTab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    boolean existsByUserFollowerIdAndUserFollowedId(Long UserFollowerId, Long UserFollowedId);
}
