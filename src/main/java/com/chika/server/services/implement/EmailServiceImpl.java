package com.chika.server.services.implement;

import com.chika.server.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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

    @Override
    public String sendHtmlMail(String mailReceiver, String token) {

        String form = "<form action=\"http://localhost:8080/user/reset-password\" method=\"post\">\n"
                + "Token: <input name=\"token\" type=\"text\"/> <br/> <br/>\n"
                + "New password: <input name=\"password\" type=\"text\"/> <br/> <br/>\n"
                + "<button type=\"submit\">CONFIRM</button>\n"
                + "</form>";

        String htmlMsg = "<h3>Auth Token: " + token + "</h3>" + form;

        MimeMessage message = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            message.setContent(htmlMsg, "text/html");

            helper.setTo(mailReceiver);
            helper.setSubject("Changing Password");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        emailSender.send(message);

        return "Email Sent!";
    }
}
