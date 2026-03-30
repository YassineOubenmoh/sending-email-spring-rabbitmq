package com.yassine.user_notification_post_linkedin.mappers;

import com.yassine.user_notification_post_linkedin.dtos.ConnectionDto;
import com.yassine.user_notification_post_linkedin.entities.Connection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ConnectionMapper {
    ConnectionMapper INSTANCE = Mappers.getMapper(ConnectionMapper.class);

    @Mapping(source = "userFollower.id", target = "followerId")
    @Mapping(source = "userFollowed.id", target = "followedId")
    ConnectionDto connectionEntityToConnectionDto(Connection connection);

    @Mapping(source = "followerId", target = "userFollower.id")
    @Mapping(source = "followedId", target = "userFollowed.id")
    @Mapping(target = "creationDateTime", ignore = true)
    @Mapping(target = "updateDateTime", ignore = true)
    @Mapping(source = "connectionStatus", target = "connectionStatus")
    Connection connectionDtoToConnectionEntity(ConnectionDto connectionDto);


}
