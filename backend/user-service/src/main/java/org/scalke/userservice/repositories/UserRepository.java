package org.scalke.userservice.repositories;

import org.scalke.userservice.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    User findAppUsersByUsername(String username);
    User findAppUsersByEmail(String email);
    User findFirstByEmail(String email);
    User findFirstByUsername(String username);
    Page<User> findAppUserByCreatedBy(Long id, Pageable pageable);

    @Modifying
    @Query("update User  u set u.deleted=false where u.id=:id")
    void deleteUserById(Long id);

    @Modifying
    @Query("update User u set u.deleted=false where u=:appUser")
    void deleteUser(User appUser);
}
