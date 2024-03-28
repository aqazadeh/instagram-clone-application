package com.aqazadeh.intagramclone.service.impl;

import com.aqazadeh.intagramclone.exception.ApplicationException;
import com.aqazadeh.intagramclone.exception.ExceptionType;
import com.aqazadeh.intagramclone.model.ConfirmationToken;
import com.aqazadeh.intagramclone.model.enums.ConfirmationType;
import com.aqazadeh.intagramclone.repository.ConfirmationTokenRepository;
import com.aqazadeh.intagramclone.service.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 16.03.2024
 * Time: 00:07
 */
@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepository repository;

    @Override
    public ConfirmationToken findByCode(String otp, ConfirmationType type) {
        ConfirmationToken confirmationToken = repository.findByTokenAndType(otp, type).orElseThrow(() -> new ApplicationException(ExceptionType.OPT_NOT_FOUND));
//        if(LocalDateTime.now().isBefore(confirmationToken.getExpireTime())){
//            throw new ApplicationException(ExceptionType.OTP_EXPIRED);
//        }
        return confirmationToken;
    }
}
