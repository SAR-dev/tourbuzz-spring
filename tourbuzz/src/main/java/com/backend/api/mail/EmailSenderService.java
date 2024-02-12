package com.backend.api.mail;

import com.backend.api.Constants;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderService {
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    public void sendHtmlMessage(EmailModel emailModel) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(emailModel.getProperties());
        helper.setTo(emailModel.getTo());
        helper.setSubject(emailModel.getSubject());
        String html = templateEngine.process(emailModel.getTemplate(), context);
        helper.setText(html, true);

        log.info("Sending emailModel: {} with html body: {}", emailModel, html);
        emailSender.send(message);
    }

    public void sendMagicLink(String recipient, String token) throws MessagingException {
        EmailModel emailModel = EmailModel.builder().to(recipient).subject("HEADER: Magic Link").template("magic-link.html").build();
        Map<String, Object> properties = new HashMap<>();
        properties.put("magicToken", token);
        emailModel.setProperties(properties);
        sendHtmlMessage(emailModel);
    }
}
