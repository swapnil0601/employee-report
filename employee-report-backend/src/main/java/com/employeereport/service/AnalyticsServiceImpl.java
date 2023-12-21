package com.employeereport.service;

import com.employeereport.entity.Report;
import com.employeereport.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class AnalyticsServiceImpl implements AnalyticsService{
    @Inject
    UserRepository userRepository;

    @Override
    public List<Object[]> getHoursWorkedReport(){
        System.out.println("Report Service");
        List<Object[]> list= userRepository.getWorkingHourReport();
        System.out.println(list);
        return list;
    }
}
