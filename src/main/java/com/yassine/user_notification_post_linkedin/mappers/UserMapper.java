package com.yassine.user_notification_post_linkedin.mappers;

import com.yassine.user_notification_post_linkedin.dtos.UserDto;
import com.yassine.user_notification_post_linkedin.entities.UserTab;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(UserTab userTab);

    UserTab userDtoToUser(UserDto userDto);
}
