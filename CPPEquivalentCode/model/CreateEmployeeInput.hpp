// CreateEmployeeInput.hpp
#pragma once
#include <string>

// Struct representing input data for creating a new Employee
struct CreateEmployeeInput {
    std::string name;    // Name of the employee
    int salary;          // Salary of the employee
    int age;             // Age of the employee
    std::string title;   // Job title of the employee
    std::string email;   // Email address of the employee

    // Constructor to initialize all fields of CreateEmployeeInput
    CreateEmployeeInput(const std::string& name,
                        int salary,
                        int age,
                        const std::string& title,
                        const std::string& email)
        : name(name), salary(salary), age(age), title(title), email(email) {}
};