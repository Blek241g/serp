package org.scalke.userservice.config.db.seeders;

import lombok.AllArgsConstructor;
import org.scalke.userservice.entities.Permission;
import org.scalke.userservice.exceptions.PermissionServiceLogicException;
import org.scalke.userservice.repositories.PermissionRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@Transactional
public class PermissionSeeder {
    public final PermissionRepository permissionRepository;

    public List<Permission> createOwnerPermissions(){
        ArrayList<String> permissionNames = new ArrayList<>();
        permissionNames.add("manage_products");
        permissionNames.add("create_products");
        permissionNames.add("show_products");
        permissionNames.add("edit_products");
        permissionNames.add("delete_products");

        return permissionRepository.saveAll(
                permissionNames.stream().map(p -> Permission.builder().name(p).build()).toList()
        );
    }

    public List<Permission> createSuperAdminPermission(){
        ArrayList<String> permissionNames = new ArrayList<>();
        permissionNames.add("manage_super_admin_dashboard");
        permissionNames.add("manage_users");
        permissionNames.add("create_users");
        permissionNames.add("show_users");
        permissionNames.add("edit_users");
        permissionNames.add("delete_users");

        permissionNames.add("manage_roles");
        permissionNames.add("create_roles");
        permissionNames.add("show_roles");
        permissionNames.add("edit_roles");
        permissionNames.add("delete_roles");

        permissionNames.add("manage_permissions");
        permissionNames.add("create_permissions");
        permissionNames.add("show_permissions");
        permissionNames.add("edit_permissions");
        permissionNames.add("delete_permissions");

        permissionNames.add("manage_plans");
        permissionNames.add("create_plans");
        permissionNames.add("show_plans");
        permissionNames.add("edit_plans");

        permissionNames.add("manage_order");


        return permissionRepository.saveAll(
                permissionNames.stream().map(p -> Permission.builder().name(p).build()).toList()
        );
    }
}
