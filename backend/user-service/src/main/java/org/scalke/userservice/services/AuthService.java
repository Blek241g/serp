package org.scalke.userservice.services;


import org.scalke.models.ResponsePlayloadApp;
import org.scalke.models.ResponseServiceApp;
import org.scalke.userservice.web.requests.*;
import org.scalke.userservice.web.responses.ChangePasswordResponse;
import org.scalke.userservice.web.responses.ForgotPasswordResponse;
import org.scalke.userservice.web.responses.LoginResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseServiceApp signup(SignupRequest request);

    LoginResponse login(LoginRequest request) throws IllegalAccessException;

    ResponsePlayloadApp checkToken();

    ChangePasswordResponse changePassword(ChangePasswordRequest request);

    ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request);

    ResponseEntity<String> createResetPasswordCode(ResetPasswordRequest request);
}
