package org.scalke.userservice.services;


import org.scalke.exceptions.AlreadyExistException;
import org.scalke.exceptions.DoesNotExistException;
import org.scalke.exceptions.NotFoundException;
import org.scalke.userservice.dtos.PermissionDTO;
import org.scalke.userservice.entities.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

public interface PermissionService {
    @PreAuthorize("hasAuthority('show_permissions')")
    Page<PermissionDTO> findPermissions(Pageable pageable);

    @PreAuthorize("hasAuthority('show_permissions')")
    Permission findPermissionById(long id) throws NotFoundException;

    @PreAuthorize("hasAuthority('show_permissions')")
    Permission findPermissionByName(String name) throws NotFoundException;

    @PreAuthorize("hasAuthority('add_permissions')")
    PermissionDTO addNewPermission(String name) throws AlreadyExistException;

    @PreAuthorize("hasAuthority('update_permissions')")
    PermissionDTO updatePermission(long id, String name) throws DoesNotExistException, AlreadyExistException;

    @PreAuthorize("hasAuthority('delete_permissions')")
    void deletePermissionById(long id) throws DoesNotExistException;



}
