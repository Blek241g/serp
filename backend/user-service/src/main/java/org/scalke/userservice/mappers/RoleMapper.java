package org.scalke.userservice.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.scalke.userservice.dtos.RoleDTO;
import org.scalke.userservice.entities.Role;
import org.scalke.userservice.repositories.RoleRepository;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {RoleRepository.class})
public abstract class RoleMapper implements MyMapper<RoleDTO, Role> {
    private RoleRepository roleRepository;
}
