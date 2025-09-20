package com.example.library.service;

import com.example.library.entity.Role;
import java.util.List;

public interface RoleService {
    Role createRole(Role role);
    List<Role> getAllRoles();
}
