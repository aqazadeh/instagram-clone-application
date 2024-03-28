package com.aqazadeh.intagramclone.service.impl;

import com.aqazadeh.intagramclone.exception.ApplicationException;
import com.aqazadeh.intagramclone.exception.ExceptionType;
import com.aqazadeh.intagramclone.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 22:08
 */
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    @Override
    public void sendMessage(String email, String subject, String key) {
            MimeMessage message = mailSender.createMimeMessage();

            try {
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                helper.setTo(email);
                helper.setFrom("noreply@aqazadeh.com");
                helper.setSubject(subject);
                helper.setText(key, true);


                mailSender.send(message);
            } catch (MessagingException e) {
                throw new ApplicationException(ExceptionType.EMAIL_SEND_ERROR);
            }

    }
}
