package com.employeereport.repository;

import com.employeereport.entity.Activity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface ActivityRepository extends CrudRepository<Activity,Integer> {

}
