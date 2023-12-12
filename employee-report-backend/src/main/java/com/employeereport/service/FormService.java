package com.employeereport.service;

import com.employeereport.entity.Form;

import java.sql.Date;
import java.util.List;

public interface FormService {
    Form createForm(Integer userId, Date date);

    Form getFormById(Integer id);
    List<Form> getUnsubmittedFormsByUserId(Integer userId);
    Form updateForm(Integer formId, Form updateForm);
}
