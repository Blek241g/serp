package org.scalke.userservice.web.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scalke.exceptions.AlreadyExistException;
import org.scalke.exceptions.DoesNotExistException;
import org.scalke.exceptions.NotFoundException;
import org.scalke.userservice.dtos.PermissionDTO;
import org.scalke.userservice.entities.Permission;
import org.scalke.userservice.services.PermissionService;
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
@AllArgsConstructor
@Slf4j
public class PermissionControllerImpl implements PermissionController {
    private PermissionService permissionService;

    @Override
    public ResponseEntity<Page<PermissionDTO>> getPermissions(int page, int size, String sortBy, boolean asc) {
        try {
                Sort sort = asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
                return ResponseEntity.ok(permissionService.findPermissions(PageRequest.of(page, size, sort)));
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Permission> getPermissionById(long id) throws NotFoundException {
        try {
            return ResponseEntity.ok(permissionService.findPermissionById(id));
        }catch (NotFoundException e){
            log.error(e.getMessage());
            throw e;
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Permission> getPermissionByName(String name) throws NotFoundException {
        try {
            return ResponseEntity.ok(permissionService.findPermissionByName(name));
        }catch (NotFoundException e){
            log.error(e.getMessage());
            throw e;
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<PermissionDTO> createPermission(String name) throws AlreadyExistException {
        try {
            PermissionDTO permission = permissionService.addNewPermission(name);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(permission.getId()).toUri();
            return ResponseEntity.created(location).body(permission);
        }catch (AlreadyExistException e){
            log.error(e.getMessage());
            throw e;
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<PermissionDTO> updatePermission(long id, String name) throws DoesNotExistException, AlreadyExistException {
        try {
            PermissionDTO permission = permissionService.updatePermission(id, name);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(permission.getId()).toUri();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location);
            return new ResponseEntity<>(permission, headers, HttpStatus.OK);
        }catch (AlreadyExistException | DoesNotExistException e){
            log.error(e.getMessage());
            throw e;
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deletePermission(long id) throws DoesNotExistException {
        try {
            permissionService.deletePermissionById(id);
        }catch (DoesNotExistException e){
            log.error(e.getMessage());
            throw e;
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
