package org.scalke.userservice.services;


import org.scalke.exceptions.AlreadyExistException;
import org.scalke.exceptions.DoesNotExistException;
import org.scalke.exceptions.NotFoundException;
import org.scalke.userservice.dtos.RoleDTO;
import org.scalke.userservice.entities.Role;
import org.scalke.userservice.exceptions.RoleServiceLogicException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

public interface RoleService {
    @PreAuthorize("hasAuthority('show_roles')")
    Page<RoleDTO> findRoles(Pageable pageable);

    @PreAuthorize("hasAuthority('show_roles')")
    Role findRoleById(long id) throws NotFoundException;

    @PreAuthorize("hasAuthority('show_roles')")
    Role findRoleByName(String name) throws NotFoundException;

    @PreAuthorize("hasAuthority('add_roles')")
    RoleDTO addNewRole(String name) throws DoesNotExistException;

    @PreAuthorize("hasAuthority('edit_roles')")
    RoleDTO updateRole(long roleId, String roleName) throws AlreadyExistException, DoesNotExistException;

    @PreAuthorize("hasAuthority('delete_roles')")
    void deleteRoleById(long id, boolean confirmDelete) throws DoesNotExistException, RoleServiceLogicException;
}
