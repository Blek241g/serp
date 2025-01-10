package org.scalke.userservice.services;

import lombok.extern.slf4j.Slf4j;
import org.scalke.exceptions.AlreadyExistException;
import org.scalke.exceptions.DoesNotExistException;
import org.scalke.exceptions.NotFoundException;
import org.scalke.userservice.dtos.PermissionDTO;
import org.scalke.userservice.entities.Permission;
import org.scalke.userservice.mappers.PermissionMapper;
import org.scalke.userservice.repositories.PermissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class PermissionServiceImpl implements PermissionService{
    private PermissionRepository permissionRepository;
    public static final String ENTITY_NAME = "Permission";
    private PermissionMapper permissionMapper;

    @Override
    public Page<PermissionDTO> findPermissions(Pageable pageable){
        try {
            return permissionRepository.findAll(pageable).map(p -> permissionMapper.entityToDto(p));
        }catch(Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Permission findPermissionById(long id) throws NotFoundException {
        return permissionRepository.findById(id).orElseThrow(() -> new NotFoundException(ENTITY_NAME, "id", String.valueOf(id)));
    }

    @Override
    public Permission findPermissionByName(String name) throws NotFoundException {
        try {
            Permission permission = permissionRepository.findFirstByName(name);
            if(permission == null) {
                throw new NotFoundException(ENTITY_NAME, "name", name);
            }
            return permission;
        }catch (NotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public PermissionDTO addNewPermission(String name) throws AlreadyExistException {
        if(!permissionRepository.existsByName(name)) {
            return permissionMapper.entityToDto(
                    permissionRepository.save(
                            Permission.builder().name(name).build()
                    )
            );
        }else {
            throw new AlreadyExistException(ENTITY_NAME, "name", name);
        }
    }

    @Override
    public PermissionDTO updatePermission(long id, String name) throws DoesNotExistException, AlreadyExistException {
      Permission permission = permissionRepository.findById(id).orElseThrow(() -> new DoesNotExistException(ENTITY_NAME, "id", String.valueOf(id)));
     if(permissionRepository.existsByName(name)) {
         throw new AlreadyExistException(ENTITY_NAME, "name", name);
     }else{
         permission.setName(name);
         return permissionMapper.entityToDto(permissionRepository.save(permission));
     }
    }

    @Override
    public void deletePermissionById(long id) throws DoesNotExistException {
        if(permissionRepository.existsById(id)) {
            permissionRepository.deleteById(id);
        }else{
            throw new DoesNotExistException(ENTITY_NAME, "id", String.valueOf(id));
        }
    }

}
