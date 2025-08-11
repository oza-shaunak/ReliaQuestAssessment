package com.reliaquest.api.model;

import lombok.Data;

@Data
public class CreateEmployeeInput {
    public CreateEmployeeInput(String string, int i, int j, String string2, String string3) {
    }
    private String name;
    private String role;
    private String department;
    private String email;
    private String phone;
}