package com.yassine.user_notification_post_linkedin.services;

import com.yassine.user_notification_post_linkedin.config.RabbitMQConfig;
import com.yassine.user_notification_post_linkedin.dtos.AcceptedFollowRequestMail;
import com.yassine.user_notification_post_linkedin.dtos.ConnectionDto;
import com.yassine.user_notification_post_linkedin.dtos.FollowRequestMail;
import com.yassine.user_notification_post_linkedin.entities.Connection;
import com.yassine.user_notification_post_linkedin.entities.UserTab;
import com.yassine.user_notification_post_linkedin.enums.ConnectionStatus;
import com.yassine.user_notification_post_linkedin.exceptions.ResourceAlreadyExistsException;
import com.yassine.user_notification_post_linkedin.exceptions.ResourceNotFoundException;
import com.yassine.user_notification_post_linkedin.mappers.ConnectionMapper;
import com.yassine.user_notification_post_linkedin.mappers.UserMapper;
import com.yassine.user_notification_post_linkedin.repositories.ConnectionRepository;
import com.yassine.user_notification_post_linkedin.repositories.UserTabRepository;
import com.yassine.user_notification_post_linkedin.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ConnectionService {

    private final ConnectionRepository connectionRepository;
    private final UserTabRepository userTabRepository;
    private final ConnectionMapper connectionMapper;
    private final UserMapper userMapper;
    private final TimeUtils timeUtils;
    private static final Logger logger = LoggerFactory.getLogger(ConnectionService.class);

    @Autowired
    public RabbitTemplate rabbitTemplate;


    public void sendFollowRequest(ConnectionDto connectionDto){
        if (connectionRepository.existsByUserFollowerIdAndUserFollowedId(connectionDto.getFollowerId(), connectionDto.getFollowedId())) {
            throw new ResourceAlreadyExistsException("The User with ID : " + connectionDto.getFollowerId() +
                    " already sent follow request to user with ID : " +
                    connectionDto.getFollowerId());
        }
        Connection connection = connectionMapper.connectionDtoToConnectionEntity(connectionDto);
        connectionRepository.save(connection);
        createFollowRequestNotification(connection);
    }

    public void changeConnectionStatus(Long connectionId) {
        Connection connection = connectionRepository.findById(connectionId).orElseThrow(
                () -> new ResourceNotFoundException("Connection with ID : " + connectionId + " was not found !"));
        connection.setConnectionStatus(ConnectionStatus.ACCEPTED);
        connectionRepository.save(connection);
        createAcceptedRequestNotification(connection);
    }


    public void createFollowRequestNotification(Connection connection) {
        UserTab userFollower = userTabRepository.findById(connection.getUserFollower().getId()).orElseThrow(
                () -> new ResourceNotFoundException("User with ID : " + connection.getUserFollowed().getId() + " was not found !"));
        FollowRequestMail followRequestMail = new FollowRequestMail(
                userMapper.userToUserDto(userFollower),
                timeUtils.formatLocalDateTime(connection.getCreationDateTime())
        );

        logger.info("The follow Request contains {}. Name of receiver is {}. Lastname of receiver is {}",
                followRequestMail.timePassed(),
                followRequestMail.userFollowed().getFirstName(),
                followRequestMail.userFollowed().getLastName()
        );
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.FOLLOW_ROUTING_KEY, followRequestMail);
    }

    public void createAcceptedRequestNotification(Connection connection) {
        UserTab userFollowed = userTabRepository.findById(connection.getUserFollowed().getId()).orElseThrow(
                () -> new ResourceNotFoundException("User with ID : " + connection.getUserFollower().getId() + " was not found !"));
        AcceptedFollowRequestMail acceptedFollowRequestMail = new AcceptedFollowRequestMail(
                userMapper.userToUserDto(userFollowed),
                timeUtils.formatLocalDateTime(connection.getCreationDateTime())
        );
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ACCEPTED_FOLLOW_ROUTING_KEY, acceptedFollowRequestMail);
    }

}
