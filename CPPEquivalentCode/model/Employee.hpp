// Employee.hpp
#pragma once
#include <string>

// Model class representing an Employee
class Employee {
    std::string id;      // Unique identifier for the employee
    std::string name;    // Name of the employee
    int salary;          // Salary of the employee
    int age;             // Age of the employee
    std::string title;   // Job title of the employee
    std::string email;   // Email address of the employee

public:
    // Constructor to initialize all fields of Employee
    Employee(const std::string& id,
             const std::string& name,
             int salary,
             int age,
             const std::string& title,
             const std::string& email);
};