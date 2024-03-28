package com.aqazadeh.intagramclone.service;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 22:06
 */

public interface MailService {
    void sendMessage(String email, String subject, String key);
}
