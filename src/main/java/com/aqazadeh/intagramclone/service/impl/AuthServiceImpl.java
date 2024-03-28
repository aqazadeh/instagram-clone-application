package com.aqazadeh.intagramclone.service.impl;

import com.aqazadeh.intagramclone.dto.request.UserChangePasswordRequest;
import com.aqazadeh.intagramclone.dto.request.UserLoginRequest;
import com.aqazadeh.intagramclone.dto.request.UserRegisterRequest;
import com.aqazadeh.intagramclone.dto.request.UserResetPasswordRequest;
import com.aqazadeh.intagramclone.dto.response.AuthResponse;
import com.aqazadeh.intagramclone.exception.ApplicationException;
import com.aqazadeh.intagramclone.exception.ExceptionType;
import com.aqazadeh.intagramclone.model.ConfirmationToken;
import com.aqazadeh.intagramclone.model.User;
import com.aqazadeh.intagramclone.model.enums.ConfirmationType;
import com.aqazadeh.intagramclone.repository.UserRepository;
import com.aqazadeh.intagramclone.service.*;
import com.aqazadeh.intagramclone.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 19:56
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final UserMapper mapper;

    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final MailService mailService;
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public void register(UserRegisterRequest request) {
        if (repository.findByUsernameAndEmail(request.getUsername(), request.getEmail()).isPresent())
            throw new ApplicationException(ExceptionType.USERNAME_OR_EMAIL_EXISTS);

        String key = UUID.randomUUID().toString();

        String hash = UUID.randomUUID().toString();
        String password = request.getPassword() + hash;
        String encodedPassword = passwordEncoder.encode(password);
        User user = mapper.toEntity(request);
        user.setPassword(encodedPassword);
        user.setPasswordHash(hash);

        ConfirmationToken token = ConfirmationToken.builder()
                .token(key)
                .user(user)
                .expireTime(LocalDateTime.now().plusMonths(1))
                .type(ConfirmationType.ACTIVATION)
                .build();

        user.setConfirmationToken(token);


        String content = "<a href=\"http://localhost:8080/api/v1/auth/confirmation?token=%s\".> click to activate</a>".formatted(key);
        mailService.sendMessage(user.getEmail(), "Eshop activate user", content);
        repository.save(user);
    }

    @Override
    public AuthResponse login(UserLoginRequest request) {
        String username = request.getUsername();
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));

        String password = request.getPassword() + user.getPasswordHash();

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new ApplicationException(ExceptionType.USER_INVALID_PASSWORD);

        if (!user.isEnabled()) {
            throw new ApplicationException(ExceptionType.USER_NOT_ACTIVATED);
        }

        if (!user.isAccountNonLocked()) {
            throw new ApplicationException(ExceptionType.USER_IS_LOCKED);
        }
        String accessToken = tokenService.generateToken(user.getUsername());

        return new AuthResponse(accessToken);
    }

    @Override
    public void confirmation(String otp) {
        User user = confirmationTokenService.findByCode(otp, ConfirmationType.ACTIVATION).getUser();
        user.setEnabled(true);
        user.getConfirmationToken().setToken("");
        repository.save(user);
    }

    @Override
    public void forgetPassword(String username) {
        User user = (User) userService.loadUserByUsername(username);
        String email = user.getEmail();
        String otp = UUID.randomUUID().toString();

        ConfirmationToken token = ConfirmationToken.builder()
                .token(otp)
                .expireTime(LocalDateTime.now().plusMinutes(3))
                .type(ConfirmationType.RESET_PASSWORD).build();
        user.setConfirmationToken(token);
        repository.save(user);
        mailService.sendMessage(email, "User forget password Request", otp);
    }

    @Override
    public void renewPassword(String otp, UserResetPasswordRequest request) {
        User user = confirmationTokenService.findByCode(otp, ConfirmationType.RESET_PASSWORD).getUser();

        if (!request.getNewPassword().equals(request.getNewPasswordRepeat())) {
            throw new ApplicationException(ExceptionType.USER_MISMATCH_PASSWORD);
        }
        String hash = user.getPasswordHash();
        String newPassword = request.getNewPassword() + hash;
        String encodeNewPassword = passwordEncoder.encode(newPassword);

        if(user.getPassword().equals(encodeNewPassword)) {
            throw new ApplicationException(ExceptionType.CURRENT_PASSWORD_NEW_PASSWORD);
        }
        user.getConfirmationToken().setToken("");
        user.setPassword(encodeNewPassword);
        repository.save(user);
    }


    @Override
    public void changePassword(User user, UserChangePasswordRequest request) {
        String hash = user.getPasswordHash();
        String password = request.getCurrentPassword() + hash;

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new ApplicationException(ExceptionType.USER_INVALID_PASSWORD);

        if (!request.getNewPassword().equals(request.getNewPasswordRepeat())) {
            throw new ApplicationException(ExceptionType.USER_MISMATCH_PASSWORD);
        }
        String newPassword = request.getNewPassword() + hash;
        String encodeNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodeNewPassword);
        repository.save(user);
    }
}
