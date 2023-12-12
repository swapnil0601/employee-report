package com.employeereport.repository;

import com.employeereport.entity.User;
import io.micronaut.context.annotation.Parameter;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    List<User> findAll();
    User findByEmail(String email);
    @Query("SELECT u from User u where u.manager IS NULL")
    List<User> findByNoManager();
    List<User> findByManager(String manager);
}
