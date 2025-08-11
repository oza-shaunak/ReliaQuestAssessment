// EmployeeController.cpp
#include "EmployeeController.hpp"

// Returns all employees by delegating to the service
std::vector<Employee> EmployeeController::getAllEmployees() {
    return service.getAllEmployees();
}

// Returns employees whose names match the search string
std::vector<Employee> EmployeeController::getEmployeesByNameSearch(const std::string& searchString) {
    return service.getEmployeesByNameSearch(searchString);
}

// Returns a single employee by ID
Employee EmployeeController::getEmployeeById(const std::string& id) {
    return service.getEmployeeById(id);
}

// Returns the highest salary among all employees
int EmployeeController::getHighestSalaryOfEmployees() {
    return service.getHighestSalaryOfEmployees();
}

// Returns the names of the top 10 highest earning employees
std::vector<std::string> EmployeeController::getTopTenHighestEarningEmployeeNames() {
    return service.getTopTenHighestEarningEmployeeNames();
}

// Creates a new employee from input
Employee EmployeeController::createEmployee(const CreateEmployeeInput& input) {
    return service.createEmployee(input);
}

// Deletes an employee by ID
void EmployeeController::deleteEmployeeById(const std::string& id) {
    service.deleteEmployeeById(id);
}