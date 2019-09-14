package com.chika.server.services.implement;

import com.chika.server.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 07-09-2019
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public String sendSimpleEmail(String mailReceiver, String token) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mailReceiver);
        message.setSubject("Chika Auth Token");
        message.setText("Welcome to Chika!\n" +
                "Here is your token: " + token);

        emailSender.send(message);

        return "Email Sent!";
    }

    @Override
    public String sendAttachmentEmail() {
        return null;
    }
}
