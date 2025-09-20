package com.example.library.mapper;

import com.example.library.dto.UserDTO;
import com.example.library.dto.UserRequestDTO;
import com.example.library.entity.Role;
import com.example.library.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(user.getRoles().stream().map(r -> r.getName()).toList())")
    UserDTO toDto(User user);

}
