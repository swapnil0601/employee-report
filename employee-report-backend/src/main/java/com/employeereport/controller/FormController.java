package com.employeereport.controller;

import com.employeereport.entity.Activity;
import com.employeereport.entity.Form;
import com.employeereport.service.ActivityService;
import com.employeereport.service.FormService;
import com.employeereport.utils.Constants;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.cors.CrossOrigin;
import jakarta.inject.Inject;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Controller("api/v1/form")
public class FormController {
    @Inject
    FormService formService;
    @Inject
    ActivityService activityService;


    private Map<String , Object> formObjectToMap(Form form){
        Map<String , Object> map = new HashMap<>();
        map.put("formId" , form.getId());
        map.put("userId" , form.getUserId());
        map.put("date" , Constants.dateToString(form.getDate()));
        return map;
    }
    @Post("/create")
    public HttpResponse<Map<String , Object>> createFormEntry(@Body Map<String , Object> requestBody){
        Integer userId = (Integer) requestBody.get("userId");
        Date date = Constants.stringToDate((String)requestBody.get("date"));
        Form form = formService.createForm(userId , date);
        return HttpResponse.created(formObjectToMap(form));
    }

    @Get("/getForm/{formId}")
    public HttpResponse<Map<String , Object>> getFormById(@PathVariable("formId") Integer formId){
        System.out.println(formId);
        try{
            Form form = formService.getFormById(formId);
            System.out.println(form);
            return HttpResponse.ok(formObjectToMap(form));
        }
        catch (RuntimeException e){
            Map<String , Object> returnObject = new HashMap<>();
            returnObject.put("error" , "form not found");
            return HttpResponse.notFound(returnObject);
        }
    }
//    @CrossOrigin("http://localhost:3000")
    @Get("/unsubmittedForms/{user_id}")
    public HttpResponse<Map<String , Object>>getUnsubmittedFormsByUserId(@PathVariable("user_id") Integer user_id){
        Map<String , Object> map = new HashMap<>();
        try {
            List<Form> unsubmittedFormList = formService.getUnsubmittedFormsByUserId(user_id);
//            System.out.println("Got the List"+unsubmittedFormList);
            List<Map<String , Object>> ret = new ArrayList<>();
            for(Form f : unsubmittedFormList){
                System.out.println(f);
                ret.add(formObjectToMap(f));
            }
            map.put("data" , ret);
            return HttpResponse.ok(map);
        }
        catch (Exception e){
            map.put("error" , "Internal Server Error");
            return HttpResponse.serverError(map);
        }
    }
//    @CrossOrigin("http://localhost:3000")
    @Post("/submit/{formId}")
    public HttpResponse<Map<String , Object>> submitForm(@PathVariable("formId") Integer formId,@Body Map<String , Object> requestBody){
        Map<String , Object> returnObject = new HashMap<>();
        try {
            Integer userId = (Integer) requestBody.get("userId");
            Form form = formService.getFormById(formId);
            Integer originalUserId = form.getUserId();

            if (!Objects.equals(userId, originalUserId)) {
                returnObject.put("msg", "Unauthorized access!");
                return HttpResponse.badRequest(returnObject);
            }

            if (form.isSubmitted()) {
                returnObject.put("msg", "Form is already submitted before.");
                return HttpResponse.badRequest(returnObject);
            }
            List<Map<String, Object>> activityList = (List<Map<String, Object>>) requestBody.get("activityList");

            for (Map<String, Object> map : activityList) {
                String category = (String) map.get("category");
                Integer minutes = (Integer) map.get("minutes");
                Activity activity = new Activity(
                        category,
                        minutes,
                        formId
                );
               activityService.createActivity(activity);
            }
            form.setSubmitted(true);
            formService.updateForm(formId, form);
            returnObject.put("msg", "Form submitted successfully.");
            return HttpResponse.created(returnObject);
        }
        catch(RuntimeException e){
            returnObject.put("msg" , e.getMessage());
            return HttpResponse.badRequest(returnObject);
        }
    }

}

