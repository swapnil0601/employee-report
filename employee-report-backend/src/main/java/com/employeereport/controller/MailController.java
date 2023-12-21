package com.employeereport.controller;

import com.employeereport.entity.Form;
import com.employeereport.entity.User;
import com.employeereport.service.FormService;
import com.employeereport.service.MailService;
import com.employeereport.service.UserService;
import com.employeereport.utils.Constants;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/api/v1/mail")
public class MailController {
    @Inject
    MailService mailService;
    @Inject
    UserService userService;
    @Inject
    FormService formService;


    @Post("/sendMail")
    public HttpResponse<Map<String ,Object>> sendMail(@Body Map<String , Object> requestBody){
        String firstName = (String) requestBody.get("firstName");
        String toEmail = (String) requestBody.get("toEmail");

        String dateStr = (String) requestBody.get("date");
        LocalDate localDate = LocalDate.parse(dateStr);
        Date date = Date.valueOf(localDate);

        String formLink = (String) requestBody.get("formLink");

        Map<String ,Object> returnObject = new HashMap<>();
        try{
            mailService.sendMail(firstName , toEmail , date , formLink);
            returnObject.put("message" ,"Mail sent successfully.");
            return HttpResponse.ok(returnObject);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            returnObject.put("message" ,"Unable to send mail!!");
            return HttpResponse.serverError(returnObject);
        }
    }
    @Post("/sendall")
    public HttpResponse<Map<String ,Object>> sendMail(){
        List<User> userList = userService.getAllUsers();
        Map<String ,Object> returnObject = new HashMap<>();
        try{
            for(User user : userList ){
                Date date = Date.valueOf(LocalDate.now());
                Form form = formService.createForm(user.getId() , date);

                // take the form_id
                // add the form_id to the form submission link . ex: server.com/submitForm/formId
                String formLink = Constants.BASE_FRONTEND_URL + "/form/" + form.getId();
                mailService.sendMail(user.getFirstName() , user.getEmail() , date , formLink);
            }
            returnObject.put("message" ,"Mail sent successfully.");
            return HttpResponse.ok(returnObject);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            returnObject.put("message" ,"Unable to send mail!!");
            return HttpResponse.serverError(returnObject);
        }
    }
}
