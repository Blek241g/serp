package org.scalke.userservice.services;


import org.scalke.userservice.entities.Permission;
import org.scalke.userservice.exceptions.PermissionServiceLogicException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PermissionService {
    Permission addNewPermission(Permission appPermission) throws PermissionServiceLogicException;
    Permission findPermissionByName(String name) throws PermissionServiceLogicException;
    Page<Permission> findPermissions(Pageable pageable) throws PermissionServiceLogicException;
    Permission findPermissionById(long id) throws PermissionServiceLogicException;
    Permission updatePermission(Permission permission) throws PermissionServiceLogicException;
    Permission updatePermission(long permissionId, String permissionName) throws PermissionServiceLogicException;
    void deletePermissionBy(long id) throws PermissionServiceLogicException;
    void deletePermission(String name) throws PermissionServiceLogicException;

}
