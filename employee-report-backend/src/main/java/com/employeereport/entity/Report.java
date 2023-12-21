package com.employeereport.entity;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Report {
    private String name;
    private Integer totalTime;

    public Report(String name, Integer totalTime) {
        this.name = name;
        this.totalTime = totalTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }
}
