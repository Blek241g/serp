package org.scalke.userservice.services;

import org.scalke.userservice.entities.Role;
import org.scalke.userservice.entities.User;
import org.scalke.userservice.exceptions.RoleUserServiceLogicException;

public interface RoleUserService {
    void addRoleToUser(Long userId, Long roleId) throws RoleUserServiceLogicException;
    void addRoleToUser(User user, Role role) throws RoleUserServiceLogicException;

}
