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

    public List<Employee> getAllEmployees() {
        Employee[] employees = restTemplate.getForObject(BASE_URL, Employee[].class);
        if (employees == null) return Collections.emptyList();
        return Arrays.asList(employees);
    }

    public List<Employee> getEmployeesByNameSearch(String searchString) {
        Employee[] employees = restTemplate.getForObject(BASE_URL, Employee[].class);
        if (employees == null) return Collections.emptyList();
        return Arrays.stream(employees)
                .filter(e -> e.getName() != null && e.getName().toLowerCase().contains(searchString.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Employee getEmployeeById(String id) {
        return restTemplate.getForObject(BASE_URL + "/" + id, Employee.class);
    }

    public Integer getHighestSalaryOfEmployees() {
        Employee[] employees = restTemplate.getForObject(BASE_URL, Employee[].class);
        if (employees == null || employees.length == 0) return 0;
        return Arrays.stream(employees)
                .mapToInt(Employee::getSalary)
                .max()
                .orElse(0);
    }

    public List<String> getTopTenHighestEarningEmployeeNames() {
        Employee[] employees = restTemplate.getForObject(BASE_URL, Employee[].class);
        if (employees == null) return Collections.emptyList();
        return Arrays.stream(employees)
                .sorted((e1, e2) -> Integer.compare(e2.getSalary(), e1.getSalary()))
                .limit(10)
                .map(Employee::getName)
                .collect(Collectors.toList());
    }

    public Employee createEmployee(CreateEmployeeInput input) {
        return restTemplate.postForObject(BASE_URL, input, Employee.class);
    }

    public void deleteEmployeeById(String id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }
}