package org.scalke.userservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.scalke.userservice.config.security.security.CustomUserDetailsService;
import org.scalke.userservice.config.security.security.TokenGenerator;
import org.scalke.userservice.web.requests.LoginRequest;
import org.scalke.userservice.web.responses.LoginResponse;
import org.scalke.userservice.web.responses.CustomToken;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
//    private ResetPasswordCodeRepository resetPasswordCodeRepository;
//    private UserService userService;
//    private RoleService roleService;
//    private PasswordEncoder passwordEncoder;
    private CustomUserDetailsService customUserDetailsService;
    private TokenGenerator tokenGenerator;
//    private JwtService jwtService;

//    @Override
//    public ResponseServiceApp signup(SignupRequest request) {
//        log.info("Signup request received {}", request);
//        try {
//            if (!userService.emailExists(request.getEmail())) {
//                Role role = roleService.findRoleByName("user");
//
//                userService.addNewUser(User.builder()
//                        .email(request.getEmail())
//                        .password(passwordEncoder.encode(request.getPassword()))
//                        .name(request.getName())
//                        .contactNumber(request.getContactNumber())
//                        .status(UserStatus.DISABLED)
//                        .roles(new HashSet<>(List.of(role)))
//                        .build()
//                );
//                return new ResponseServiceApp(ResponseServiceState.SUCCESS, CafeConstants.SUCCESSFULLY_REGISTERED);
//            }else{
//                return new ResponseServiceApp(ResponseServiceState.BAD, CafeConstants.EMAIL_ALREADY_EXISTS);
//
//            }
//        }catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//       return new ResponseServiceApp(ResponseServiceState.ERROR, CafeConstants.SOMETHING_WENT_WRONG);
//
//    }

    @Override
    public LoginResponse login(LoginRequest request) {
        CustomToken token = new CustomToken();
        token.setUserId(customUserDetailsService.getUserDetails().getUserid());
        token.setAccessToken(tokenGenerator.generateAccessToken(
                Map.of("authorities", customUserDetailsService.getUserDetails().authoritiesToString()),
                customUserDetailsService.getUserDetails().getUsername()
        ));
        log.info("token {}", token);
        return LoginResponse.builder()
                .token(token)
                .message("Login successfully!")
                .build();
    }
//
//    @Override
//    @PreAuthorize("hasRole('ROLE_user')")
//    public ResponsePlayloadApp checkToken() {
//        ResponsePlayloadApp res = new ResponsePlayloadApp();
//        res.setMessage("Token check successful");
//        res.setStatus(HttpStatus.OK);
//        res.setPlayload(Map.of("checked", true));
//        return res;
//    }
//
//    @Override
//    @PreAuthorize("hasRole('ROLE_user')")
//    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
//        ChangePasswordResponse response = new ChangePasswordResponse();
//        try {
//            User user = userService.findByEmail(customUserDetailsService.getUserDetails().getUsername());
//            if(Objects.nonNull(user)) {
//                BiFunction<String, String, Boolean> comparePassword = (pass1, pass2) -> {
//                    return passwordEncoder.matches(pass1, pass2);
//                };
//                if(comparePassword.apply(request.getOldPassword(), user.getPassword())){
//                   if(!Objects.equals(request.getOldPassword(), request.getNewPassword())){
//                       user.setPassword(passwordEncoder.encode(request.getNewPassword()));
//                       userService.updateUser(user);
//                       CafeUtils.getResponseEntity("Password updated successfully", HttpStatus.OK, response);
//                       return response;
//                   }else{
//                       CafeUtils.getResponseEntity("New password must be different to the old password !", HttpStatus.BAD_REQUEST, response);
//                    }
//                }else{
//                    CafeUtils.getResponseEntity("Incorrect old password", HttpStatus.BAD_REQUEST, response);
//                }
//            }else{
//                CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR, response);
//            }
//            return response;
//
//        }catch (Exception e){
//            log.error(e.getMessage(), e);
//        }
//        CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR, response);
//        return response;
//    }
//
//    @Override
//    public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request) {
//        ForgotPasswordResponse response = new ForgotPasswordResponse();
//        try {
//            User user = userService.findByEmail(request.getEmail());
////            ResetPasswordCode reset = resetPasswordCodeRepository.findByCodeAndUser(request.getCode(), user);
//            ResetPasswordCode passwordCode = user.getResetPasswordCodeList().stream().filter(
//                    c -> Objects.equals(c.getCode(), request.getCode()) && Objects.equals(c.getUser().getId(), user.getId())
//            ).findFirst().orElse(null);
//            if(Objects.nonNull(passwordCode)) {
//                String defaultPassword = "password2";
//                String passwordEncoded = passwordEncoder.encode(defaultPassword);
//                userService.updateUserPassword(user, passwordEncoded);
//                response.setTempPassword(defaultPassword);
//                CafeUtils.getResponseEntity("Password updated successfully", HttpStatus.OK, response);
//            }else {
//                response.setMessage("Invalid credentials");
//                response.setStatus(HttpStatus.BAD_REQUEST);
//                return response;
//            }
//            return response;
//        }catch (Exception e){
//            log.error(e.getMessage(), e);
//        }
//        CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR, response);
//        return response;
//    }
//
//    @Override
//    public ResponseEntity<String> createResetPasswordCode(ResetPasswordRequest request) {
//        try {
//            User user = userService.findByEmail(request.getUserEmail());
//            if(Objects.nonNull(user)) {
//
//                ResetPasswordCode resetPasswordCode = new ResetPasswordCode();
//                resetPasswordCode.setCode((String.valueOf(Double.valueOf(Math.random()*7777).intValue())));
//                resetPasswordCode.setUser(user);
//                resetPasswordCode.setCreatedAt(LocalDateTime.now());
//                resetPasswordCode.setExpirationDate(LocalDateTime.now().plusMinutes(10L));
//                ResetPasswordCode resetPasswordCode1= resetPasswordCodeRepository.save(resetPasswordCode);
//
//                if (resetPasswordCode1.getId()!=null) {
//                    ((UserServiceImpl) userService).getEmailUtils().sendEmail(user.getEmail(), "Reset password code", "Code : "+resetPasswordCode1.getCode()+"\n" +
//                            "It expire in: "+(resetPasswordCode1.getExpirationDate().minusMinutes(resetPasswordCode1.getCreatedAt().getMinute())), null);
//                    return CafeUtils.getResponseEntity("Code is created successfully", HttpStatus.OK);
//                }else {
//                    return CafeUtils.getResponseEntity("Code created failed", HttpStatus.INTERNAL_SERVER_ERROR);
//                }
//            }else{
//                return CafeUtils.getResponseEntity("Bad credentials", HttpStatus.BAD_REQUEST);
//            }
//        }catch (Exception e){
//            log.error(e.getMessage(), e);
//        }
//        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
