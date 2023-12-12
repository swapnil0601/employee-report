package com.employeereport.service;

import com.employeereport.entity.Activity;
import com.employeereport.repository.ActivityRepository;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class ActivityServiceImpl implements ActivityService{
    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

}
