package org.scalke.userservice.config.db.seeders;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scalke.userservice.entities.Permission;
import org.scalke.userservice.entities.Role;
import org.scalke.userservice.entities.User;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class Seeder implements ApplicationListener<ContextRefreshedEvent> {
    private PermissionSeeder permissionSeeder;
    private RoleSeeder roleSeeder;
    private UserSeeder userSeeder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<Permission> adminsPermission = permissionSeeder.createSuperAdminPermission();
        List<Permission> ownerPermission = permissionSeeder.createOwnerPermissions();

        Role adminRole = roleSeeder.createAdminRole(adminsPermission);
        Role ownerRole = roleSeeder.createOwnerRole(ownerPermission);
        Role regularRole = roleSeeder.createRegularRole(List.of());

        User adminUser = userSeeder.createUserAdmin(adminRole);
        User ownerUser = userSeeder.createUserOwner(ownerRole);
        User regularUser = userSeeder.createUserRegular(regularRole, ownerUser);

        userSeeder.createUser(10, ownerUser, null);
    }
}
