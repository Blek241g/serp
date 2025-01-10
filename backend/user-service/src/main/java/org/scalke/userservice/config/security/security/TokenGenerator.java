package org.scalke.userservice.config.security.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.function.Consumer;

@Component
@AllArgsConstructor
public class TokenGenerator {
    private JwtEncoder encoder;

    public String generateAccessToken(Map<String, Object> claims, String subject){

        Instant now = Instant.now();
        Consumer<Map<String, Object>> claimsConsumer = map -> {};
        claimsConsumer.accept(claims);

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("scalke-erp")
                .issuedAt(now)
                .expiresAt(now.plus(30, ChronoUnit.MINUTES))
                .subject(subject)
                .claims(claimsConsumer)
                .build();
        return encoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }
}
