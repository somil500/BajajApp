package com.example.App.model;

public class UserRequest {
    private String name;
    private String regNo;
    private String email;

    public UserRequest(String name, String regNo, String email) {
        this.name = name;
        this.regNo = regNo;
        this.email = email;
    }

    public String getName() { return name; }
    public String getRegNo() { return regNo; }
    public String getEmail() { return email; }
}
