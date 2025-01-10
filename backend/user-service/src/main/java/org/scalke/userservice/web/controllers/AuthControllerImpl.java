package org.scalke.userservice.web.controllers;

import com.blek.camas.constants.CafeConstants;
import com.blek.camas.models.ResponsePlayloadApp;
import com.blek.camas.models.ResponseServiceApp;
import com.blek.camas.helpers.CafeUtils;
import com.blek.camas.userModule.services.AuthService;
import com.blek.camas.userModule.web.requests.SignupRequest;
import com.blek.camas.web.requests.*;
import com.blek.camas.web.responses.ChangePasswordResponse;
import com.blek.camas.web.responses.ForgotPasswordResponse;
import com.blek.camas.web.responses.LoginResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class AuthControllerImpl implements AuthController {

    private AuthService authService;
    private AuthenticationManager authenticationManager;


    @Override
    public ResponseEntity<String> signup(SignupRequest request){

        try {
            ResponseServiceApp res = authService.signup(request);
            switch (res.getState()) {
                case SUCCESS -> {
                    return CafeUtils.getResponseEntity(res.getMessage(), HttpStatus.CREATED);
                }
                case BAD -> {
                    return CafeUtils.getResponseEntity(res.getMessage(), HttpStatus.BAD_REQUEST);
                }
                case ERROR -> {
                    return CafeUtils.getResponseEntity(res.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request){
        try {

                Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
                if(authentication.isAuthenticated()){
                    LoginResponse res = authService.login(request);
                     return new ResponseEntity<>(res, res.getToken() !=null ? HttpStatus.OK:HttpStatus.UNAUTHORIZED);
                }
                else{
                    return new ResponseEntity<>(LoginResponse.builder().message("Bad credentials!").build()
                            , HttpStatus.BAD_REQUEST);
                }
        }catch (DisabledException e){
            return new ResponseEntity<>(LoginResponse.builder()
                    .message("Wait for admin approval!")
                    .build(), HttpStatus.FORBIDDEN);
        }
        catch (BadCredentialsException e) {
            return new ResponseEntity<>(LoginResponse.builder().message("Bad credentials!").build()
                    , HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(
                LoginResponse.builder()
                        .message((CafeConstants.SOMETHING_WENT_WRONG))
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_user')")
    public ResponseEntity<String> checkToken() {
        try {
            ResponsePlayloadApp res = authService.checkToken();
            String checked = null;
            if(res.getPlayload().containsKey("checked")){
               checked = String.valueOf(res.getPlayload().get("checked"));
            }
            return new ResponseEntity<>(checked, HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_user')")
    public ResponseEntity<ChangePasswordResponse> changePassword(ChangePasswordRequest request) {
        ChangePasswordResponse response = new ChangePasswordResponse();
        try {
            response = authService.changePassword(request);
            return new ResponseEntity<>(response, response.getStatus());
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }

        CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR, response);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Override
    public ResponseEntity<String> createResetPasswordCode(ResetPasswordRequest request) {
        try {
            return authService.createResetPasswordCode(request);
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ForgotPasswordResponse> forgotPassword(ForgotPasswordRequest request) {
        ForgotPasswordResponse response = new ForgotPasswordResponse();
        try {
            response = authService.forgotPassword(request);
            return new ResponseEntity<>(response, response.getStatus());
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR, response);
        return new ResponseEntity<>(response, response.getStatus());
    }
}