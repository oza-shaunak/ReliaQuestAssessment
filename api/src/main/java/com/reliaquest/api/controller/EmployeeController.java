package com.reliaquest.api.controller;

import com.reliaquest.api.exception.ApiExceptions;
import com.reliaquest.api.model.CreateEmployeeInput;
import com.reliaquest.api.model.Employee;
import com.reliaquest.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * EmployeeController implements the IEmployeeController interface and
 * delegates business logic to EmployeeService. It handles HTTP requests.
 */
@RestController
public class EmployeeController implements IEmployeeController<Employee, CreateEmployeeInput> {

    // Service class to handle business logic
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Returns all employees.
     */
    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            return ResponseEntity.ok(employeeService.getAllEmployees());
        } catch (Exception ex) {
            throw new ApiExceptions.ExternalServiceException("Failed to fetch all employees.");
        }
    }

    /**
     * Returns employees whose names contain the given search string.
     */
    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {
        try {
            return ResponseEntity.ok(employeeService.getEmployeesByNameSearch(searchString));
        } catch (Exception ex) {
            throw new ApiExceptions.ExternalServiceException("Failed to search employees by name.");
        }
    }

    /**
     * Returns a single employee by ID, or 404 if not found.
     */
    @Override
    public ResponseEntity<Employee> getEmployeeById(String id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            if (employee == null) {
                throw new ApiExceptions.EmployeeNotFoundException(id);
            }
            return ResponseEntity.ok(employee);
        } catch (ApiExceptions.EmployeeNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiExceptions.ExternalServiceException("Failed to fetch employee by id: " + id);
        }
    }

    /**
     * Returns the highest salary among all employees.
     */
    @Override
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        try {
            return ResponseEntity.ok(employeeService.getHighestSalaryOfEmployees());
        } catch (Exception ex) {
            throw new ApiExceptions.ExternalServiceException("Failed to fetch highest salary.");
        }
    }

    /**
     * Returns the names of the top 10 highest earning employees.
     */
    @Override
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        try {
            return ResponseEntity.ok(employeeService.getTopTenHighestEarningEmployeeNames());
        } catch (Exception ex) {
            throw new ApiExceptions.ExternalServiceException("Failed to fetch top 10 highest earning employee names.");
        }
    }

    /**
     * Creates a new employee and returns the created entity.
     */
    @Override
    public ResponseEntity<Employee> createEmployee(CreateEmployeeInput employeeInput) {
        try {
            Employee created = employeeService.createEmployee(employeeInput);
            return ResponseEntity.ok(created);
        } catch (Exception ex) {
            throw new ApiExceptions.ExternalServiceException("Failed to create employee.");
        }
    }

    /**
     * Deletes an employee by ID and returns a success message.
     */
    @Override
    public ResponseEntity<String> deleteEmployeeById(String id) {
        try {
            employeeService.deleteEmployeeById(id);
            return ResponseEntity.ok("Employee deleted successfully");
        } catch (Exception ex) {
            throw new ApiExceptions.ExternalServiceException("Failed to delete employee with id: " + id);
        }
    }
}