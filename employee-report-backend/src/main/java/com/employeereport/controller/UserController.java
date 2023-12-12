package com.employeereport.controller;

import com.employeereport.entity.User;
import com.employeereport.service.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.cors.CrossOrigin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {this.userService=userService;}

    @CrossOrigin("http://localhost:3000")
    @Post("/register")
    public HttpResponse<User> register(@Body User user){
        return HttpResponse.created(userService.register(user));
    }

    @CrossOrigin("http://localhost:3000")
    @Post("/login")
    public HttpResponse<Map<String, Object>> loginUser(@Body Map<String, Object> map) {
        String email = (String) map.get("email");
        String password = (String) map.get("password");
        Map<String, Object> returnObject = new HashMap<>();
        try {
            User user = userService.login(email, password);
            returnObject.put("user", user);
            return HttpResponse.ok(returnObject);
        } catch (Exception e) {
            returnObject.put("error", e.getMessage());
            return HttpResponse.badRequest(returnObject);
        }
    }

    @Get("/all")
    public HttpResponse<List<User>> getAllUsers() {
        return HttpResponse.ok(userService.getAllUsers());
    }

    @Get("/getUser/{id}")
    public HttpResponse<User> getUserById(@PathVariable("id") Integer userId) {
        return HttpResponse.ok(userService.getUserById(userId));
    }
    @Get("/getUsersWithNoManager")
    public HttpResponse<Map<String, Object>> getUsersWithNoManager() {
        Map<String, Object> ret = new HashMap<>();
        try {
            List<User> userList = userService.getUsersWithNoManager();
            ret.put("data", userList);
            return HttpResponse.ok(ret);
        } catch (Exception e) {
            ret.put("msg", "Internal Server Error");
            return HttpResponse.serverError(ret);
        }
    }

    @Post("/getUsersByManager")
    public HttpResponse<Map<String, Object>> getMembersByManagerId(@Body Map<String, Object> map) {
        Map<String, Object> ret = new HashMap<>();
        String manager = (String) map.get("manager");
//        System.out.println(manager);
        try {
            List<User> userList = userService.getUsersByManager(manager);
            ret.put("data", userList);
            return HttpResponse.ok(ret);
        } catch (Exception e) {
            ret.put("msg", "Internal Server Error");
            return HttpResponse.serverError(ret);
        }
    }
    @Put("/updateUser/{id}")
    public HttpResponse<User> updateUser(@PathVariable("id") Integer id, @Body User user) {
        return HttpResponse.ok(userService.updateUser(id, user));
    }

    @Delete("/deleteUser/{id}")
    public HttpResponse<String> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return HttpResponse.ok("User Deleted!!");
    }
}
