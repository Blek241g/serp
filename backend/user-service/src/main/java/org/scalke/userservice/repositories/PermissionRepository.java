package org.scalke.userservice.repositories;

import org.scalke.userservice.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findAppPermissionByName(String name);

    Permission findFirstByName(final String name);
}
