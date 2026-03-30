package com.yassine.user_notification_post_linkedin.services;

import com.yassine.user_notification_post_linkedin.dtos.UserDto;
import com.yassine.user_notification_post_linkedin.entities.UserTab;
import com.yassine.user_notification_post_linkedin.exceptions.ResourceAlreadyExistsException;
import com.yassine.user_notification_post_linkedin.exceptions.ResourceNotFoundException;
import com.yassine.user_notification_post_linkedin.mappers.UserMapper;
import com.yassine.user_notification_post_linkedin.repositories.UserTabRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserTabService {

    private final UserTabRepository userTabRepository;
    private final UserMapper userMapper;

    public void addUser(UserDto userDto) {
        if (userTabRepository.existsByUsername(userDto.getUsername())) {
            throw new ResourceAlreadyExistsException("The user called : " + userDto.getUsername() + " does not exists !");
        }
        userTabRepository.save(userMapper.userDtoToUser(userDto));
    }

    public Set<UserDto> getAllUsers(){
        return userTabRepository.findAll().stream()
                .map(userMapper::userToUserDto)
                .limit(10)
                .collect(Collectors.toSet());
    }

    public UserDto getUserById(Long userId){
        UserTab userTab = userTabRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with ID " + userId + " was not found !"));
        return userMapper.userToUserDto(userTab);
    }

}
