package com.backend.api.mail;

import com.backend.api.Constants;
import com.backend.api.request.MagicLinkMailData;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    Resend resend = new Resend(Constants.RESEND_API_KEY);

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    public void sendHtmlMessage(EmailModel emailModel) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(emailModel.getProperties());
        helper.setTo(emailModel.getTo());
        helper.setSubject(emailModel.getSubject());
        String html = templateEngine.process(emailModel.getHtmlTemplate(), context);
        helper.setText(html, true);

        log.info("Sending emailModel by mail: {} with html body: {}", emailModel, html);
        emailSender.send(message);
    }

    public void sendEmailByResend(EmailModel emailModel) throws ResendException {
        Context context = new Context();
        context.setVariables(emailModel.getProperties());
        String html = templateEngine.process(emailModel.getHtmlTemplate(), context);
        String text = templateEngine.process(emailModel.getTextTemplate(), context);
        CreateEmailOptions params = CreateEmailOptions.builder()
                .from(Constants.RESEND_SENDER)
                .to(emailModel.getTo())
                .subject(emailModel.getSubject())
                .html(html)
                .text(text)
                .build();

        log.info("Sending emailModel by resend: {} with text: {}", emailModel, text);
        CreateEmailResponse data = resend.emails().send(params);
        log.info(data.toString());
    }

    public void sendMagicLink(MagicLinkMailData mailData) throws Exception {
        EmailModel emailModel = EmailModel.builder()
                .to(mailData.getEmail())
                .subject(Constants.MAGIC_LINK.MAIL_HEADER)
                .htmlTemplate(Constants.TEMPLATES.MAGIC_LINK_HTML)
                .textTemplate(Constants.TEMPLATES.MAGIC_LINK_TEXT)
                .build();
        Map<String, Object> properties = new HashMap<>();
        properties.put("magicKey", mailData.getKey());
        properties.put("magicToken", mailData.getToken());
        properties.put("ip", mailData.getIp());
        properties.put("device", mailData.getDevice());
        properties.put("location", mailData.getLocation());
        properties.put("clientLink", Constants.MAGIC_LINK.TOKEN_VERIFY_URL);
        emailModel.setProperties(properties);
        sendEmailByResend(emailModel);
    }
}
