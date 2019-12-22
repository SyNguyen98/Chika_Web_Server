package com.chika.server.services;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendSimpleEmail(String mailReceiver, String token);

    void sendAttachmentEmail();

    void sendHtmlMail(String mailReceiver, String token);
}
