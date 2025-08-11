// EmployeeService.hpp
#pragma once
#include "Employee.hpp"
#include "CreateEmployeeInput.hpp"
#include "ApiExceptions.hpp"
#include <vector>
#include <algorithm>
#include <string>

// Service class for managing Employee objects
class EmployeeService {
public:
    std::vector<Employee> employees; // List of all employees

    // Returns all employees in the system
    std::vector<Employee> getAllEmployees();

    // Returns employees whose names contain the search string
    std::vector<Employee> getEmployeesByNameSearch(const std::string& searchString);

    // Returns the employee with the given ID, or throws if not found
    Employee getEmployeeById(const std::string& id);

    // Returns the highest salary among all employees
    int getHighestSalaryOfEmployees();

    // Returns the names of the top 10 highest earning employees
    std::vector<std::string> getTopTenHighestEarningEmployeeNames();

    // Creates a new employee from input and adds to the list
    Employee createEmployee(const CreateEmployeeInput& input);

    // Deletes an employee by ID, throws if not found
    void deleteEmployeeById(const std::string& id);
};