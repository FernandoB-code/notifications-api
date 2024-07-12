package com.arabot.notificatiosapi.notificatiosapi.service;

import com.arabot.notificatiosapi.notificatiosapi.dto.MessageDTO;
import com.arabot.notificatiosapi.notificatiosapi.dto.ResumeProduct;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Service
@Log4j2
public class NotificationServiceImpl {

    @Value("${rabbitmq.queue}")
    String queueName;

    @Value("${rabbitmq.exchange}")
    String exchange;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private ObjectMapper objectMapper;

    private final String SUBJECT_MESSAGE = "Summary of your purchase";


    @RabbitListener(queues = "${rabbitmq.queue}")
    public void handleMessage(String message) {
        log.info("Received message from queue " + queueName + ": " + message);
        try {
            MessageDTO messageDTO = objectMapper.readValue(message, MessageDTO.class);
            sendEmail(messageDTO.getUserEmail(), this.SUBJECT_MESSAGE, messageDTO.getProductList(), messageDTO.getTotalAmount());
        } catch (Exception e) {
            log.error("Failed to send email", e);
        }
    }

    private void sendEmail(String to, String subject,  List<ResumeProduct> products, double total) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom(fromEmail);

        Context context = new Context();
        context.setVariable("products", products);
        context.setVariable("total", total);


        String html = templateEngine.process("emailTemplate", context);
        helper.setText(html, true);

        mailSender.send(message);
    }
}
