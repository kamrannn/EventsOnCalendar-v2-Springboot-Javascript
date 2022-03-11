package org.daypilot.demo.html5eventcalendarspring.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotification {

    private JavaMailSender javaMailSender;

    public EmailNotification(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String email, String message) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email, email);
        msg.setSubject("Event Reminder");
        msg.setText(message);
        javaMailSender.send(msg);
    }
}
