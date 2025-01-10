package org.scalke.userservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scalke.exceptions.AlreadyExistException;
import org.scalke.exceptions.DoesNotExistException;
import org.scalke.exceptions.NotFoundException;
import org.scalke.userservice.dtos.RoleDTO;
import org.scalke.userservice.entities.Role;
import org.scalke.userservice.exceptions.RoleServiceLogicException;
import org.scalke.userservice.mappers.RoleMapper;
import org.scalke.userservice.repositories.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    private RoleMapper roleMapper;
    private static final String ENTITY_NAME = "role";

    @Override
    public Page<RoleDTO> findRoles(Pageable pageable){
        return roleRepository.findAll(pageable).map(r -> roleMapper.entityToDto(r));
    }

    @Override
    public Role findRoleByName(String name) throws NotFoundException {
        Role role = roleRepository.findFirstByName(name);
        if(role != null)
            return role;
        else
            throw new NotFoundException(ENTITY_NAME, "name", name);
    }

    @Override
    public Role findRoleById(long id) throws NotFoundException {
        return roleRepository.findById(id).orElseThrow( () -> new NotFoundException(ENTITY_NAME, "id", String.valueOf(id)));
    }

    @Override
    public RoleDTO addNewRole(String name) throws DoesNotExistException {
        if(!roleRepository.existsByName(name)){
            return roleMapper.entityToDto(
                    roleRepository.save(Role.builder().name(name).build())
            );
        }else
            throw new DoesNotExistException(ENTITY_NAME, "name", name);
    }

    @Override
    public RoleDTO updateRole(long id, String name) throws AlreadyExistException, DoesNotExistException {
        Role role = roleRepository.findById(id).orElseThrow(() -> new DoesNotExistException(ENTITY_NAME, "id", String.valueOf(id)));
        if(!roleRepository.existsByName(name)){
            role.setName(name);
            return roleMapper.entityToDto(roleRepository.save(role));
        }else{
            throw new AlreadyExistException(ENTITY_NAME, "name", name);
        }
    }


    @Override
    public void deleteRoleById(long id, boolean confirmDelete) throws DoesNotExistException, RoleServiceLogicException {
        Role role = roleRepository.findById(id).orElse(null);
        if(role != null){
            if(role.getPermissions().isEmpty()){
                roleRepository.delete(role);
            }else{
                if(confirmDelete){
                    roleRepository.delete(role);
                }
                throw new RoleServiceLogicException("Can't delete role with permissions", HttpStatus.CONFLICT);
            }
        }else{
            throw new DoesNotExistException(ENTITY_NAME, "id", String.valueOf(id));
        }
    }
}
