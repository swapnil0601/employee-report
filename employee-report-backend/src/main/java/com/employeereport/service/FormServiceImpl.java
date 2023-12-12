package com.employeereport.service;

import com.employeereport.entity.Form;
import com.employeereport.repository.FormRepository;
import jakarta.inject.Singleton;

import java.sql.Date;
import java.util.List;

@Singleton
public class FormServiceImpl implements FormService{
    private final FormRepository formRepository;

    public FormServiceImpl(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @Override
    public Form createForm(Integer userId, Date date) {
        Form form = new Form(userId, date);
        return formRepository.save(form);
    }

    @Override
    public Form getFormById(Integer formId) throws RuntimeException {
        return formRepository.findById(formId).orElseThrow(() -> new RuntimeException("form not found"));
    }

    @Override
    public List<Form> getUnsubmittedFormsByUserId(Integer userId){
        try{
            return formRepository.findUnSubmittedFormsByUserId(userId);
        }
        catch(Exception e){
            return null;
        }
    }
    @Override
    public Form updateForm(Integer formId , Form updatedForm){
        Form prevForm = getFormById(formId);
        prevForm.setUserId(updatedForm.getUserId());
        prevForm.setDate(updatedForm.getDate());
        prevForm.setSubmitted(updatedForm.isSubmitted());
        System.out.println(prevForm.isSubmitted());
        return formRepository.update(prevForm);
    }
}
