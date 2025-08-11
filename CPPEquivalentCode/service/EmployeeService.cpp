// EmployeeService.cpp
#include "EmployeeService.hpp"

// Returns all employees in the system
std::vector<Employee> EmployeeService::getAllEmployees() {
    return employees;
}

// Returns employees whose names contain the search string
std::vector<Employee> EmployeeService::getEmployeesByNameSearch(const std::string& searchString) {
    std::vector<Employee> result;
    for (const auto& e : employees) {
        if (e.name.find(searchString) != std::string::npos) {
            result.push_back(e);
        }
    }
    return result;
}

// Returns the employee with the given ID, or throws if not found
Employee EmployeeService::getEmployeeById(const std::string& id) {
    for (const auto& e : employees) {
        if (e.id == id) return e;
    }
    throw EmployeeNotFoundException(id);
}

// Returns the highest salary among all employees
int EmployeeService::getHighestSalaryOfEmployees() {
    if (employees.empty()) return 0;
    int maxSalary = 0;
    for (const auto& e : employees) {
        if (e.salary > maxSalary) maxSalary = e.salary;
    }
    return maxSalary;
}

// Returns the names of the top 10 highest earning employees
std::vector<std::string> EmployeeService::getTopTenHighestEarningEmployeeNames() {
    std::vector<Employee> sorted = employees;
    std::sort(sorted.begin(), sorted.end(), [](const Employee& a, const Employee& b) {
        return b.salary < a.salary;
    });
    std::vector<std::string> names;
    for (size_t i = 0; i < sorted.size() && i < 10; ++i) {
        names.push_back(sorted[i].name);
    }
    return names;
}

// Creates a new employee from input and adds to the list
Employee EmployeeService::createEmployee(const CreateEmployeeInput& input) {
    Employee e;
    e.id = std::to_string(employees.size() + 1); // simple id
    e.name = input.name;
    e.salary = input.salary;
    e.age = input.age;
    e.title = input.title;
    e.email = input.email;
    employees.push_back(e);
    return e;
}

// Deletes an employee by ID, throws if not found
void EmployeeService::deleteEmployeeById(const std::string& id) {
    auto it = std::remove_if(employees.begin(), employees.end(), [&](const Employee& e) {
        return e.id == id;
    });
    if (it == employees.end()) {
        throw EmployeeNotFoundException(id);
    }
    employees.erase(it, employees.end());
}