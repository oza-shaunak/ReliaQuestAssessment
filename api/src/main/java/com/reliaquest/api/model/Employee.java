package com.reliaquest.api.model;

import lombok.Data;

@Data
public class Employee {
    public Employee(String string, String string2, int i, int j, String string3, String string4) {

    }
    private String id;
    private String name;
    private String role;
    private String department;
    private String email;
    private String phone;
    public int salary;
}