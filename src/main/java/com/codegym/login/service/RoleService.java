package com.codegym.login.service;

import com.codegym.login.model.Role;

public interface RoleService {
    Role findRoleByName(String roleName);
}
