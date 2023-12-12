package com.employeereport.repository;

import com.employeereport.entity.Form;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface FormRepository extends CrudRepository<Form,Integer> {
    @Query("Select f from Form f where f.userId=:userId and not f.submitted")
    List<Form> findUnSubmittedFormsByUserId(Integer userId);
}
