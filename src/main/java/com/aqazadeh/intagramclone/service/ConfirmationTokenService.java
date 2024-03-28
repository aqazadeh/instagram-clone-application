package com.aqazadeh.intagramclone.service;

import com.aqazadeh.intagramclone.model.ConfirmationToken;
import com.aqazadeh.intagramclone.model.enums.ConfirmationType;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 23:59
 */

public interface ConfirmationTokenService {
    ConfirmationToken findByCode(String otp, ConfirmationType type);
}
