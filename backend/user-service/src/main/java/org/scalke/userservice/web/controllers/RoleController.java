package org.scalke.userservice.web.controllers;

import jakarta.websocket.server.PathParam;
import org.scalke.exceptions.AlreadyExistException;
import org.scalke.exceptions.DoesNotExistException;
import org.scalke.exceptions.NotFoundException;
import org.scalke.userservice.dtos.RoleDTO;
import org.scalke.userservice.entities.Role;
import org.scalke.userservice.exceptions.RoleServiceLogicException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/roles")
public interface RoleController {

    @PreAuthorize("hasAuthority('show_roles')")
    @GetMapping("")
    public ResponseEntity<Page<RoleDTO>> getRoles(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "id") String sortBy,
                                                     @RequestParam(defaultValue = "asc") boolean asc
    );

    @PreAuthorize("hasAuthority('show_roles')")
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") long id) throws NotFoundException;

    @PreAuthorize("hasAuthority('show_roles')")
    @GetMapping("/name/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable("name") String name) throws NotFoundException;

    @PreAuthorize("hasAuthority('add_roles')")
    @PostMapping("")
    public ResponseEntity<RoleDTO> createRole(@RequestBody String name) throws AlreadyExistException, DoesNotExistException;

    @PreAuthorize("hasAuthority('update_roles')")
    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable("id") long id, @RequestBody String name) throws AlreadyExistException, DoesNotExistException;

    @PreAuthorize("hasAuthority('delete_roles')")
    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable("id") long id, @RequestParam(name = "confirm", defaultValue = "true") boolean confirmDelete) throws NotFoundException, DoesNotExistException, RoleServiceLogicException;


}
