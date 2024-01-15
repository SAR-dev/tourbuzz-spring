package com.backend.api.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@RequiredArgsConstructor
public class EmailService {
    @Value("${spring.mail.username}")
    private String mailFrom;

    private final MailSender mailSender;

    public void sendMail(String[] mailTo, String mailSubject, String mailMessage){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailFrom);
        message.setTo(mailTo);
        message.setSubject(mailSubject);
        message.setText(mailMessage);
        mailSender.send(message);
    }
}
