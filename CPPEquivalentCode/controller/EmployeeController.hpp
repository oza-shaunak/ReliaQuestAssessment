// EmployeeController.hpp
#pragma once
#include "EmployeeService.hpp"
#include <vector>
#include <string>

// Controller class for handling Employee-related requests
class EmployeeController {
    EmployeeService& service; // Reference to the service layer
public:
    // Constructor that takes a reference to EmployeeService
    explicit EmployeeController(EmployeeService& s) : service(s) {}

    // Returns all employees
    std::vector<Employee> getAllEmployees();

    // Returns employees whose names match the search string
    std::vector<Employee> getEmployeesByNameSearch(const std::string& searchString);

    // Returns a single employee by ID
    Employee getEmployeeById(const std::string& id);

    // Returns the highest salary among all employees
    int getHighestSalaryOfEmployees();

    // Returns the names of the top 10 highest earning employees
    std::vector<std::string> getTopTenHighestEarningEmployeeNames();

    // Creates a new employee from input
    Employee createEmployee(const CreateEmployeeInput& input);

    // Deletes an employee by ID
    void deleteEmployeeById(const std::string& id);
};