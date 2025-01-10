package org.scalke.userservice.config.db.seeders;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scalke.userservice.constants.UserType;
import org.scalke.userservice.entities.Permission;
import org.scalke.userservice.entities.Role;
import org.scalke.userservice.entities.User;
import org.scalke.userservice.exceptions.PermissionServiceLogicException;
import org.scalke.userservice.exceptions.RoleServiceLogicException;
import org.scalke.userservice.exceptions.UserServiceLogicException;
import org.scalke.userservice.mappers.UserMapper;
import org.scalke.userservice.repositories.UserRepository;
import org.scalke.userservice.services.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@Slf4j
@Transactional
@AllArgsConstructor
public class UserSeeder  {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private final Faker faker =  new Faker(Locale.of("fr"));

    public User createUserAdmin(Role adminRole) {
        return userRepository.save(User.builder()
                .email("admin@example.com")
                .password(passwordEncoder.encode("1234"))
                .username("admin")
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .phoneNumber(faker.phoneNumber().phoneNumber())
                .roles(List.of(adminRole))
                .isActive(true)
                .avatar("admin.jpeg")
                .createdBy(null)
                .userType(UserType.ADMIN)
                .emailVerifiedAt(LocalDateTime.now())
                .phoneNumberVerifiedAt(LocalDateTime.now())
                .deleted(false)
                .build());
    }

    public User createUserOwner(Role ownerRole) {
        return userRepository.save(User.builder()
                .email("owner@example.com")
                .password(passwordEncoder.encode("1234"))
                .username("owner")
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .phoneNumber(faker.phoneNumber().phoneNumber())
                .roles(List.of(ownerRole))
                .isActive(true)
                .avatar("owner.jpeg")
                .createdBy(null)
                .userType(UserType.OWNER)
                .emailVerifiedAt(LocalDateTime.now())
                .phoneNumberVerifiedAt(LocalDateTime.now())
                .deleted(false)
                .build());
    }

    public User createUserRegular(Role regularRole, User owner) {
        return userRepository.save(User.builder()
                .email("regular@example.com")
                .password(passwordEncoder.encode("1234"))
                .username("regular")
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .phoneNumber(faker.phoneNumber().phoneNumber())
                .roles(List.of(regularRole))
                .isActive(true)
                .avatar("regular.jpeg")
                .createdBy(owner.getId())
                .userType(UserType.REGULAR)
                .emailVerifiedAt(LocalDateTime.now())
                .phoneNumberVerifiedAt(LocalDateTime.now())
                .deleted(false)
                .build());
    }

    public List<User> createUser(int n, User owner, Role role){
        List<User> users = new ArrayList<>();
        for(int i=0; i<n; i++){
            double d = Math.random();
            String u = "user" +i;
            users.add(User.builder()
                    .email(u+"@example.com")
                    .password(passwordEncoder.encode("1234"))
                    .username(u)
                    .firstname(faker.name().firstName())
                    .lastname(faker.name().lastName())
                    .phoneNumber(faker.phoneNumber().phoneNumber())
                    .isActive(d > 0.5)
                    .avatar(u+".jpeg")
                    .createdBy(owner.getId())
                    .userType(UserType.REGULAR)
                    .emailVerifiedAt(LocalDateTime.of(2025, 1, 7, 0, 0))
                    .phoneNumberVerifiedAt(LocalDateTime.of(2025, 1, 7, 0, 0))
                    .deleted(d > 0.3)
                    .deletedAt( d > 0.3 ? LocalDateTime.of(2025, 3, 7, 0, 0):null)
                    .build());
        }
        return userRepository.saveAll(users);
    }
}
