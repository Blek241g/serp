package org.scalke.userservice.config.security.security;

import org.scalke.userservice.entities.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class JwtAuthConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt source) {
//        Collection<GrantedAuthority> authorities = Stream.concat(
//                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
//                extractResourceRoles(jwt).stream()
//        ).collect(Collectors.toSet());
//        return new JwtAuthenticationToken(jwt, authorities, jwt.getClaim("preferred_username"));
        User user = new User();
        user.setUsername(source.getSubject());
        return new UsernamePasswordAuthenticationToken(user, source, Collections.EMPTY_LIST);
    }

//    private Collection<GrantedAuthority> extractResourceRoles(Jwt jwt) {
//        Map<String, Object> realmAccess = jwt.getClaims();
//        Collection<String> roles;
//
//        if(jwt.getClaim("realm_access") == null) {
//            return Set.of();
//        }
//        realmAccess = jwt.getClaim("realm_access");
//        roles = (Collection<String>) realmAccess.get("roles");
//        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
//    }
}
