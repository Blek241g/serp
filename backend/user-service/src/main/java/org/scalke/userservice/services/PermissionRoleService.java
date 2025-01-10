package org.scalke.userservice.services;

import org.scalke.userservice.entities.Permission;
import org.scalke.userservice.entities.Role;
import org.scalke.userservice.exceptions.PermissionRoleServiceLogicException;

public interface PermissionRoleService {
    Role addPermissionToRole(Long roleId, Long permissionId) throws PermissionRoleServiceLogicException;
    Role addPermissionToRole(String role, String permission) throws PermissionRoleServiceLogicException;
    Role addPermissionToRole(Role role, Permission permission) throws PermissionRoleServiceLogicException;
    Role removePermissionFromRole(Long roleId, Long permissionId) throws PermissionRoleServiceLogicException;
    Role removePermissionFromRole(String role, String permission) throws PermissionRoleServiceLogicException;
    Role removePermissionFromRole(Role appRole, Permission appPermission) throws PermissionRoleServiceLogicException;

}
