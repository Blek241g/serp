package org.scalke.userservice.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.scalke.userservice.dtos.PermissionDTO;
import org.scalke.userservice.entities.Permission;
import org.scalke.userservice.repositories.PermissionRepository;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {PermissionRepository.class})
public abstract class PermissionMapper implements MyMapper<PermissionDTO, Permission> {
    private PermissionRepository permissionRepository;
}
