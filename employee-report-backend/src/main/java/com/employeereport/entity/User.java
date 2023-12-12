package com.employeereport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String firstName;
    private String lastName;

    @NotBlank
    private String email;

    private LocalTime workStartHour;

    private LocalTime workEndHour;

    @NotBlank
    private String password;

    private String manager;
    public  User(){}
    public User(int id, String firstName, String lastName, String email, LocalTime workStartHour, LocalTime workEndHour, String password
                ,String manager
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.workStartHour = workStartHour;
        this.workEndHour = workEndHour;
        this.password = password;
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalTime getWorkStartHour() {
        return workStartHour;
    }

    public void setWorkStartHour(LocalTime workStartHour) {
        this.workStartHour = workStartHour;
    }

    public LocalTime getWorkEndHour() {
        return workEndHour;
    }

    public void setWorkEndHour(LocalTime workEndHour) {
        this.workEndHour = workEndHour;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
