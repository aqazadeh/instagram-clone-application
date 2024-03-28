package com.aqazadeh.intagramclone.service.impl;

import com.aqazadeh.intagramclone.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 22:00
 */
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.key}")
    private String key;
    @Override
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + (7200 * 1000)))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(getKey())
                .compact();
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        Claims claims = getClaims(token);
        String username = claims.getSubject();
        Date expireDate = claims.getExpiration();
        return userDetails.getUsername().equals(username) && !expireDate.before(new Date());
    }

    private Key getKey() {
        byte[] keyByte = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
