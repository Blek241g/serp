package org.scalke.userservice.config.security.security;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.scalke.userservice.entities.User;
import org.scalke.userservice.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Slf4j
@Service
@Getter
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private CustomUserDetails userDetails;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("In UserDetailsService loadUserByUsername {}", username);
        User user = userRepository.findFirstByEmail(username);
        if (!Objects.isNull(user)) {
            userDetails = new CustomUserDetails(user);
            return userDetails;
        } else {
            throw new UsernameNotFoundException("User " + username + " not found!");
        }
    }
}
