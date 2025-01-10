package org.scalke.userservice.config.security.security;

import lombok.Getter;
import org.scalke.userservice.entities.Permission;
import org.scalke.userservice.entities.Role;
import org.scalke.userservice.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final String email;
    private final String password;
    private final List<GrantedAuthority> authorities;
    @Getter
    private long userid;
    private final boolean enabled;
    @Getter
    private boolean deleted;

    public CustomUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = getGrantedAuthorities(getPrivileges(user.getRoles()));
        this.userid = user.getId();
        this.enabled = user.getIsActive();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
       return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    private List<String> getPrivileges(Collection<Role> roles){
        List<String> privileges = new ArrayList<>();
        for(Role role : roles){
            privileges.add("ROLE_"+role.getName());
            privileges.addAll( role.getPermissions().stream().map(Permission::getName).toList());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String privilege : privileges){
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    public List<String> authoritiesToString() {
        return authorities.stream().map(GrantedAuthority::getAuthority).toList();
    }
}
