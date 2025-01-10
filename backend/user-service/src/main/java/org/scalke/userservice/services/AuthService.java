package org.scalke.userservice.services;


import org.scalke.userservice.web.requests.*;
import org.scalke.userservice.web.responses.ChangePasswordResponse;
import org.scalke.userservice.web.responses.ForgotPasswordResponse;
import org.scalke.userservice.web.responses.LoginResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
//    ResponseServiceApp signup(SignupRequest request);
//
    LoginResponse login(LoginRequest request);
//
//    ResponsePlayloadApp checkToken();
//
//    ChangePasswordResponse changePassword(ChangePasswordRequest request);
//
//    ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request);
//
//    ResponseEntity<String> createResetPasswordCode(ResetPasswordRequest request);
}
