package org.scalke.userservice.web.controllers;

import org.scalke.exceptions.AlreadyExistException;
import org.scalke.exceptions.DoesNotExistException;
import org.scalke.exceptions.NotFoundException;
import org.scalke.userservice.dtos.PermissionDTO;
import org.scalke.userservice.entities.Permission;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/permissions")
public interface PermissionController {

    @PreAuthorize("hasAuthority('show_permissions')")
    @GetMapping("")
    public ResponseEntity<Page<PermissionDTO>> getPermissions(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size,
                                                              @RequestParam(defaultValue = "id") String sortBy,
                                                              @RequestParam(defaultValue = "asc") boolean asc
    );

    @PreAuthorize("hasAuthority('show_permissions')")
    @GetMapping("/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable("id") long id) throws NotFoundException;

    @PreAuthorize("hasAuthority('show_permissions')")
    @GetMapping("/name/{name}")
    public ResponseEntity<Permission> getPermissionByName(@PathVariable("name") String name) throws NotFoundException;

    @PreAuthorize("hasAuthority('add_permissions')")
    @PostMapping("")
    public ResponseEntity<PermissionDTO> createPermission(@RequestBody String name) throws AlreadyExistException;

    @PreAuthorize("hasAuthority('update_permissions')")
    @PutMapping("/{id}")
    public ResponseEntity<PermissionDTO> updatePermission(@PathVariable("id") long id, @RequestBody String name) throws AlreadyExistException, DoesNotExistException;

    @PreAuthorize("hasAuthority('delete_permissions')")
    @DeleteMapping("/{id}")
    public void deletePermission(@PathVariable("id") long id) throws NotFoundException, DoesNotExistException;


}
