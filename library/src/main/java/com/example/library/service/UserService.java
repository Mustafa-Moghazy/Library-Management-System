package com.example.library.service;

import com.example.library.dto.UserDTO;
import com.example.library.dto.UserRequestDTO;
import com.example.library.entity.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserRequestDTO user);
    User getUserById(Long id);
    List<UserDTO> getAllUsers();
    void deleteUser(Long id);
}
