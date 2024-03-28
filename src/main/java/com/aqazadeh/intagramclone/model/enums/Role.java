package com.aqazadeh.intagramclone.model.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 10.03.2024
 * Time: 19:21
 */

public enum Role implements GrantedAuthority {
    ADMIN,
    USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
