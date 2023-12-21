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

        String htmlContent = "<html>\n" +
                "<body>\n" +
                "  <p>Dear "+firstName+",</p>\n" +
                "\n" +
                "  <p>We kindly request your participation in providing details regarding today's tasks, dated "+date+". Your input is invaluable in our efforts.</p>\n" +
                "\n" +
                "  <p>Please take a moment to complete the following form:</p>\n" +
                "  \n" +
                "  <p><a href=\""+formLink+"\">"+formLink+"</a></p>\n" +
                "\n" +
                "  <p>Thank you for your cooperation,</p>\n" +
                "  <p>The Daily Work Report Team</p>\n" +
                "</body>\n" +
                "</html>";

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
