package org.scalke.userservice.services;


import org.scalke.userservice.entities.Role;
import org.scalke.userservice.exceptions.RoleServiceLogicException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    Role addNewRole(Role appRole) throws RoleServiceLogicException;
    Role findAppRoleByName(String name) throws RoleServiceLogicException;
    Role findAppRoleById(long id) throws RoleServiceLogicException;
    Page<Role> findAllAppRoles(Pageable pageable) throws RoleServiceLogicException;
    Role updateAppRole(Role appRole) throws RoleServiceLogicException;
    Role updateAppRole(long roleId, String roleName) throws RoleServiceLogicException;
    void deleteAppRole(long id) throws RoleServiceLogicException;
    void deleteAppRole(Role appRole) throws RoleServiceLogicException;
    void deleteAppRole(String name) throws RoleServiceLogicException;
}
