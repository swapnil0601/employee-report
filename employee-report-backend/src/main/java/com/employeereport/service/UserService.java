package com.employeereport.service;

import com.employeereport.entity.User;

import java.util.List;

public interface UserService {
    User register(User user);
    User login(String email,String password) throws Exception;
    User getUserById(Integer id);
    List<User> getAllUsers();
    List<User> getUsersWithNoManager();
    List<User> getUsersByManager(String manager);
    User updateUser(Integer id,User user);
    void deleteUser(Integer id);
}
