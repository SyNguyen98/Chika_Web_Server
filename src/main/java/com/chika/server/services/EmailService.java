package com.chika.server.services;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    String sendSimpleEmail(String mailReceiver, String token);

    String sendAttachmentEmail();

    String sendHtmlMail(String mailReceiver, String token);
}
