package org.scalke.userservice.web.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class LoginResponse {
    private CustomToken token;
    private String message;
}
