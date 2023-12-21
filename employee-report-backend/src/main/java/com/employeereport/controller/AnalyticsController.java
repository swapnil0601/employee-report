package com.employeereport.controller;

import com.employeereport.entity.Report;
import com.employeereport.entity.User;
import com.employeereport.service.ActivityService;
import com.employeereport.service.AnalyticsService;
import com.employeereport.service.FormService;
import com.employeereport.service.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("api/v1/analytics")
public class AnalyticsController {
    @Inject
    AnalyticsService analyticsService;

    @Get("/timeReport")
    public HttpResponse<Map<String,Object>> getTimeReport(){
        System.out.println("Report Controller");
        Map<String, Object> ret = new HashMap<>();
        try {
            List<Object[]> reportList = analyticsService.getHoursWorkedReport();
            ret.put("data", reportList);
            return HttpResponse.ok(ret);
        } catch (Exception e) {
            ret.put("msg", "Internal Server Error");
            return HttpResponse.serverError(ret);
        }
    }

}
