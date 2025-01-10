package org.scalke.userservice.repositories;

import org.scalke.userservice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findAppRoleByName(String name);
    Role findFirstByName(String name);
}
