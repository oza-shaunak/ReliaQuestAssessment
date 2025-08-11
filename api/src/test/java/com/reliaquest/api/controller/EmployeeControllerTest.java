package com.reliaquest.api.controller;

import com.reliaquest.api.model.CreateEmployeeInput;
import com.reliaquest.api.model.Employee;
import com.reliaquest.api.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(
                new Employee("1", "John Doe", 1000, 30, "Engineer", "john@company.com"),
                new Employee("2", "Jane Smith", 2000, 28, "Manager", "jane@company.com")
        );
        when(employeeService.getAllEmployees()).thenReturn(employees);

        ResponseEntity<List<Employee>> response = employeeController.getAllEmployees();

        assertEquals(2, response.getBody().size());
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void testGetEmployeesByNameSearch() {
        List<Employee> employees = Arrays.asList(
                new Employee("2", "Jane Smith", 2000, 28, "Manager", "jane@company.com")
        );
        when(employeeService.getEmployeesByNameSearch("Jane")).thenReturn(employees);

        ResponseEntity<List<Employee>> response = employeeController.getEmployeesByNameSearch("Jane");

        assertEquals(1, response.getBody().size());
        assertEquals("Jane Smith", response.getBody().get(0).getName());
    }

    @Test
    void testGetEmployeeById() {
        Employee employee = new Employee("1", "John Doe", 1000, 30, "Engineer", "john@company.com");
        when(employeeService.getEmployeeById("1")).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.getEmployeeById("1");

        assertEquals("John Doe", response.getBody().getName());
    }

    @Test
    void testGetHighestSalaryOfEmployees() {
        when(employeeService.getHighestSalaryOfEmployees()).thenReturn(2000);

        ResponseEntity<Integer> response = employeeController.getHighestSalaryOfEmployees();

        assertEquals(2000, response.getBody());
    }

    @Test
    void testGetTopTenHighestEarningEmployeeNames() {
        List<String> names = Arrays.asList("Alice", "Jane Smith", "John Doe");
        when(employeeService.getTopTenHighestEarningEmployeeNames()).thenReturn(names);

        ResponseEntity<List<String>> response = employeeController.getTopTenHighestEarningEmployeeNames();

        assertEquals(names, response.getBody());
    }

    @Test
    void testCreateEmployee() {
        CreateEmployeeInput input = new CreateEmployeeInput("Jane Smith", 2000, 28, "Manager", "jane@company.com");
        Employee created = new Employee("2", "Jane Smith", 2000, 28, "Manager", "jane@company.com");
        when(employeeService.createEmployee(input)).thenReturn(created);

        ResponseEntity<Employee> response = employeeController.createEmployee(input);

        assertEquals("Jane Smith", response.getBody().getName());
    }

    @Test
    void testDeleteEmployeeById() {
        doNothing().when(employeeService).deleteEmployeeById("1");

        ResponseEntity<String> response = employeeController.deleteEmployeeById("1");

        assertEquals("Employee deleted successfully", response.getBody());
        verify(employeeService, times(1)).deleteEmployeeById("1");
    }
}