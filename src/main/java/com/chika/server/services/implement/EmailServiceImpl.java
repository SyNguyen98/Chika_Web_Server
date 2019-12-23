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
 * @since 22-12-2019
 */
@Service
public class EmailServiceImpl implements EmailService {

    public final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendSimpleEmail(String mailReceiver, String token) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mailReceiver);
        message.setSubject("Chika Auth Token");
        message.setText("Welcome to Chika!\n" +
                "Here is your token: " + token);

        emailSender.send(message);
    }

    @Override
    public void sendAttachmentEmail() {
    }

    @Override
    public void sendHtmlMail(String mailReceiver, String token) {

        String form = "<form action=\"http://chika-server.herokuapp.com/user/reset-password\" method=\"post\">\n"
                + "Token: <input name=\"token\" type=\"text\" style=\"width:600px;font-size:12pt;\"/> <br/> <br/>\n"
                + "New password: <input name=\"password\" type=\"text\" style=\"width:200px;font-size:12pt;\"/> <br/> <br/>\n"
                + "<button type=\"submit\">CONFIRM</button>\n"
                + "</form>";

        String htmlMsg = "<p>Auth Token: " + token + "</p>" + form;

        MimeMessage message = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            message.setContent(htmlMsg, "text/html");

            helper.setTo(mailReceiver);
            helper.setSubject("Chika Smarthome: Reset Password");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        emailSender.send(message);
    }
}
