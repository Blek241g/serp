package org.scalke.userservice.config.security.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.scalke.userservice.entities.Role;
import org.scalke.userservice.entities.User;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserInfo {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
//    private UserStatus status;
    private String password;
    private List<Role> roles;

    public UserInfo(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
//        this.status = user.getStatus();
        this.password = user.getPassword();
        this.roles = user.getRoles().stream().toList();
    }
}
