package com.employeereport.service;

import jakarta.inject.Singleton;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.sql.Date;
import java.util.Properties;

@Singleton
public class MailServiceImpl implements MailService{

    String from = "awesomeback143@gmail.com";
    // DEV credentials
    final String username = "awesomeback143@gmail.com";
    final String password = "swasd asda adhasdhasjdhuw";

    String host = "smtp.gmail.com";
    @Override
    public void sendMail(String firstName, String toEmail, Date date, String formLink) {
    Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        String htmlContent = "<html><body><p>Dear " + firstName + ", <br> Hope your day went well .<br>"+
                "Please fill the following form to provide details about the tasks you did today (" + date + ") .  <br>" +
                "<br>" + formLink + "<br>"+
                "<br>Thank you,<br>"+
                "Daily Work Report Team</p>"+
                "</body></html>";

        try {
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));

        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(toEmail));

        message.setSubject("Daily work report");

        message.setContent(htmlContent, "text/html");
        Transport.send(message);

        System.out.println("Email has been sent successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
}
}
