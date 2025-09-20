package com.example.library.mapper;

import com.example.library.dto.UserActivityDTO;
import com.example.library.entity.UserActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserActivityMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.userName", target = "userName")
    UserActivityDTO toDto(UserActivity activity);

}
