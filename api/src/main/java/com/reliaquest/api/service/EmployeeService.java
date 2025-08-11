package com.reliaquest.api.service;

import com.reliaquest.api.model.CreateEmployeeInput;
import com.reliaquest.api.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private static final String BASE_URL = "http://localhost:8112/api/v1/employee";
    private final RestTemplate restTemplate;

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Fetches all employees from the remote service.
     * Returns an empty list if the API call fails or returns nothing.
     */
    public List<Employee> getAllEmployees() {
        Employee[] employeeArray = restTemplate.getForObject(BASE_URL, Employee[].class);

        if (employeeArray == null) {
            // Fail gracefully — don't throw, just return empty
            return Collections.emptyList();
        }

        return Arrays.asList(employeeArray);
    }

    /**
     * Finds employees whose names contain the given search string (case-insensitive).
     * If no employees are found, returns an empty list.
     */
    public List<Employee> getEmployeesByNameSearch(String searchString) {
        Employee[] employeeArray = restTemplate.getForObject(BASE_URL, Employee[].class);

        if (employeeArray == null) {
            return Collections.emptyList();
        }

        String lowerSearch = searchString.toLowerCase(Locale.ROOT);

        return Arrays.stream(employeeArray)
                .filter(e -> e.getName() != null && e.getName().toLowerCase(Locale.ROOT).contains(lowerSearch))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a single employee by ID.
     * Returns null if the employee is not found.
     */
    public Employee getEmployeeById(String id) {
        return restTemplate.getForObject(BASE_URL + "/" + id, Employee.class);
    }

    /**
     * Calculates the highest salary among all employees.
     */
    public Integer getHighestSalaryOfEmployees() {
        Employee[] employeeArray = restTemplate.getForObject(BASE_URL, Employee[].class);

        if (employeeArray == null || employeeArray.length == 0) {
            return 0;
        }
        return Arrays.stream(employeeArray)
                .mapToInt(Employee::getSalary) 
                .max()
                .orElse(0);
    }

    /**
     * Gets the names of the top 10 highest-paid employees.
     */
    public List<String> getTopTenHighestEarningEmployeeNames() {
        Employee[] employeeArray = restTemplate.getForObject(BASE_URL, Employee[].class);

        if (employeeArray == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(employeeArray)
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .limit(10)
                .map(Employee::getName)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Sends a request to create a new employee.
     */
    public Employee createEmployee(CreateEmployeeInput input) {
        return restTemplate.postForObject(BASE_URL, input, Employee.class);
    }

    /**
     * Deletes an employee by ID.
     */
    public void deleteEmployeeById(String id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }
}
