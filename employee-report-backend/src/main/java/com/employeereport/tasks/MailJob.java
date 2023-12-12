package com.employeereport.tasks;

import com.employeereport.entity.Form;
import com.employeereport.entity.User;
import com.employeereport.service.FormService;
import com.employeereport.service.MailService;
import com.employeereport.service.UserService;
import com.employeereport.utils.Constants;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Singleton
public class MailJob {

    private final UserService userService;
    private final FormService formService;
    private final MailService mailService;

    public MailJob(UserService userService, FormService formService, MailService mailService) {
        this.userService = userService;
        this.formService = formService;
        this.mailService = mailService;
    }
    //        @Scheduled(fixedDelay = "5s")
    // */10 * * * * ?"
    // 0 0 18 * * MON-FRI
//    @Scheduled(cron = "0 0 18 * * MON-FRI")
    void execute(){
        List<User> userList = userService.getAllUsers();
        for(User user : userList ){
            Date date = Date.valueOf(LocalDate.now());
            Form form = formService.createForm(user.getId() , date);

            // take the form_id
            // add the form_id to the form submission link . ex: server.com/submitForm/formId
            String formLink = Constants.BASE_FRONTEND_URL + "/form/" + form.getId();

            mailService.sendMail(user.getFirstName() , user.getEmail() , date , formLink);
        }
    }
}
