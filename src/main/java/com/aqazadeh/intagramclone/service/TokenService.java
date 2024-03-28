package com.aqazadeh.intagramclone.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 12.03.2024
 * Time: 22:09
 */

public interface TokenService {

    String generateToken(String username);

    Claims getClaims(String token);

    boolean validateToken(String token, UserDetails userDetails);
}
