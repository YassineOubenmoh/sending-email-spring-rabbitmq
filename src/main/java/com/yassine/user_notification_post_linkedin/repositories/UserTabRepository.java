package com.yassine.user_notification_post_linkedin.repositories;

import com.yassine.user_notification_post_linkedin.entities.UserTab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTabRepository extends JpaRepository<UserTab, Long> {
    boolean existsByUsername(String username);
}
