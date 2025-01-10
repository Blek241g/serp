package org.scalke.userservice.web.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scalke.exceptions.AlreadyExistException;
import org.scalke.exceptions.DoesNotExistException;
import org.scalke.exceptions.NotFoundException;
import org.scalke.userservice.dtos.RoleDTO;
import org.scalke.userservice.entities.Role;
import org.scalke.userservice.exceptions.RoleServiceLogicException;
import org.scalke.userservice.services.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@Slf4j
@AllArgsConstructor
public class RoleControllerImpl implements RoleController {
    private RoleService roleService;

    @Override
    public ResponseEntity<Page<RoleDTO>> getRoles(int page, int size, String sortBy, boolean asc) {
        try {
            Sort sort = asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
            return ResponseEntity.ok(roleService.findRoles(PageRequest.of(page, size, sort)));
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Role> getRoleById(long id) throws NotFoundException {
        try {
            return ResponseEntity.ok(roleService.findRoleById(id));
        }catch (NotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Role> getRoleByName(String name) throws NotFoundException {
        try {
            return ResponseEntity.ok(roleService.findRoleByName(name));
        }catch (NotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<RoleDTO> createRole(String name) throws AlreadyExistException, DoesNotExistException {
        try {
            RoleDTO role = roleService.addNewRole(name);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(role.getId()).toUri();
            return ResponseEntity.created(location).body(role);
        } catch (DoesNotExistException e) {
            log.error(e.getMessage());
            throw e;
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<RoleDTO> updateRole(long id, String name) throws AlreadyExistException, DoesNotExistException {
        try {
            RoleDTO role = roleService.updateRole(id, name);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(role.getId()).toUri();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location);
            return new ResponseEntity<>(role, headers, HttpStatus.OK);
        } catch (DoesNotExistException | AlreadyExistException e) {
            log.error(e.getMessage());
            throw e;
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteRole(long id, boolean confirmDelete) throws DoesNotExistException, RoleServiceLogicException {
        try {
            roleService.deleteRoleById(id, confirmDelete);
        }catch (RoleServiceLogicException | DoesNotExistException e){
            log.error(e.getMessage());
            throw e;
        }
    }
}
