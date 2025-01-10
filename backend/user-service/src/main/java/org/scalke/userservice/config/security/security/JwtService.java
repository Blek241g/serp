package org.scalke.userservice.config.security.security;

import com.nimbusds.jwt.JWT;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithms;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;


import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
@Getter @Setter
@Slf4j
@AllArgsConstructor
public class JwtService {

    private JwtEncoder encoder;
    private JwtDecoder decoder;

    public String extractUsername(String token) {
        return getSubject(token);
    }

    public List<GrantedAuthority> extractAuthorities(String token) {
//        String key = "authorities";
//        List<String> authorities = Arrays.asList(token.split(","));
//        if(extractAllClaims(token).containsKey(key) && extractAllClaims(token).get(key) instanceof List) {
//            List<?> authorities = extractAllClaims(token).get(key, List.class);
//            if(authorities.get(0) instanceof LinkedHashMap<?,?>){
//                return new ArrayList<GrantedAuthority>(
//                        authorities.stream().map(a -> {
//                            LinkedHashMap<?,?> map = (LinkedHashMap<?,?>) a;
//                            return new SimpleGrantedAuthority((String) map.get("authority"));
//                        }).toList()
//                );
//            }
//            return new ArrayList<GrantedAuthority>();
//        }
        return null;
    }


    public Instant extractExpiration(String token) {
        return getJwt(token).getExpiresAt();
    }

    public String getSubject(String token) {
        return getJwt(token).getSubject();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).isBefore(Instant.now());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = this.extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public <T> T extractClaim(String token, String claim) {
        return getJwt(token).getClaim(claim);
    }

    public Map<String, Object> extractAllClaims(String token) {
       return getJwt(token).getClaims();

    }

    private Jwt getJwt(String token) {
        return decoder.decode(token);
    }
}
