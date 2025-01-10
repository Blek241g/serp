package org.scalke.userservice.repositories;

import org.scalke.userservice.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findFirstByName(final String name);
    boolean existsByName(String name);
    boolean existsById(long id);

}
