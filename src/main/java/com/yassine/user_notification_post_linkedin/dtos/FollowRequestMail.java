package com.yassine.user_notification_post_linkedin.dtos;

import com.yassine.user_notification_post_linkedin.entities.UserTab;

public record FollowRequestMail(
        UserDto userFollowed,
        String timePassed
) {
}
