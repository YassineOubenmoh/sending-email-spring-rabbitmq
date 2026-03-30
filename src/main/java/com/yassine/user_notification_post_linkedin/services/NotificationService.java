package com.yassine.user_notification_post_linkedin.services;

import com.yassine.user_notification_post_linkedin.config.RabbitMQConfig;
import com.yassine.user_notification_post_linkedin.dtos.AcceptedFollowRequestMail;
import com.yassine.user_notification_post_linkedin.dtos.FollowRequestMail;
import com.yassine.user_notification_post_linkedin.dtos.UserDto;
import com.yassine.user_notification_post_linkedin.entities.UserTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NotificationService {

    private final EmailService emailService;

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);


    public NotificationService(EmailService emailService) {
        this.emailService = emailService;
    }


    @RabbitListener(queues = RabbitMQConfig.FOLLOW_QUEUE, containerFactory = "rabbitListenerContainerFactory")
    public void consumeFollowRequestMessage(FollowRequestMail message) {
        UserDto userDto = message.userFollowed();
        Map<String, Object> model = new HashMap<>();
        model.put("firstname", userDto.getFirstName());
        model.put("lastname", userDto.getLastName());
        model.put("timePassed", message.timePassed());

        try {
            logger.info("Attempt to send", userDto.getEmail());
            emailService.sendNotification(userDto.getEmail(), "Follow Request", model, "follow-request-email-template.ftl", null);

            } catch (Exception e) {
                logger.error("Failed sent email", userDto.getEmail(), e.getMessage(), e);
            }
    }

    @RabbitListener(queues = RabbitMQConfig.ACCEPTED_FOLLOW_QUEUE, containerFactory = "rabbitListenerContainerFactory")
    public void consumeAcceptedFollowRequestMessage(AcceptedFollowRequestMail message) {
        UserDto user = message.userFollower();
        Map<String, Object> model = new HashMap<>();
        model.put("firstname", user.getFirstName());
        model.put("lastname", user.getLastName());
        model.put("timePassed", message.timePassed());

        try {
            logger.info("Attempt to send", user.getEmail());
            emailService.sendNotification(user.getEmail(), "Accepted Follow Request", model, "accepted-request-template.ftl", null);

        } catch (Exception e) {
            logger.error("Failed sent email", user.getEmail(), e.getMessage(), e);
        }
    }


}