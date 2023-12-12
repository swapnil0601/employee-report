package com.employeereport.service;

import java.sql.Date;

public interface MailService {
    void sendMail(String firstName, String toEmail, Date date,String formLink);
}
