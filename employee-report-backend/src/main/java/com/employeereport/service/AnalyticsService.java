package com.employeereport.service;

import com.employeereport.entity.Report;

import java.util.List;

public interface AnalyticsService {
    List<Object[]> getHoursWorkedReport();
}
