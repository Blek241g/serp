package org.scalke.userservice.config.db.seeders;

import lombok.AllArgsConstructor;
import org.scalke.userservice.entities.Permission;
import org.scalke.userservice.entities.Role;
import org.scalke.userservice.repositories.RoleRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
@Transactional
public class RoleSeeder {
    private RoleRepository roleRepository;

    public Role createAdminRole(List<Permission> adminPermissions){
        return roleRepository.save(Role.builder()
                .permissions(adminPermissions)
                .name("admin")
                .build()
        );
    }

    public Role createOwnerRole(List<Permission> ownerPermissions){
        return roleRepository.save(Role.builder()
                .permissions(ownerPermissions)
                .name("owner")
                .build()
        );
    }

    public Role createRegularRole(List<Permission> regularPermissions){
        return roleRepository.save(Role.builder()
                .permissions(regularPermissions)
                .name("regular")
                .build()
        );
    }

    public Role createRole(String name){
       return roleRepository.save(Role.builder().name(name).build());
    }
}
