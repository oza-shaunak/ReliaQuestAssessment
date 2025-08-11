#include "gtest/gtest.h"
#include "../service/EmployeeService.hpp"
#include "../model/CreateEmployeeInput.hpp"

TEST(EmployeeServiceTest, CreateEmployeeAndGetById) {
    EmployeeService service;
    CreateEmployeeInput input("Alice", 70000, 35, "Lead", "alice@example.com");
    Employee emp = service.createEmployee(input);

    EXPECT_EQ(emp.name, "Alice");
    EXPECT_EQ(emp.salary, 70000);
    EXPECT_EQ(emp.age, 35);
    EXPECT_EQ(emp.title, "Lead");
    EXPECT_EQ(emp.email, "alice@example.com");

    Employee fetched = service.getEmployeeById(emp.id);
    EXPECT_EQ(fetched.name, "Alice");
}

TEST(EmployeeServiceTest, GetHighestSalary) {
    EmployeeService service;
    service.createEmployee(CreateEmployeeInput("A", 1000, 20, "T1", "a@a.com"));
    service.createEmployee(CreateEmployeeInput("B", 2000, 21, "T2", "b@b.com"));
    service.createEmployee(CreateEmployeeInput("C", 1500, 22, "T3", "c@c.com"));

    EXPECT_EQ(service.getHighestSalaryOfEmployees(), 2000);
}

TEST(EmployeeServiceTest, GetTopTenHighestEarningEmployeeNames) {
    EmployeeService service;
    for (int i = 0; i < 15; ++i) {
        service.createEmployee(CreateEmployeeInput("Emp" + std::to_string(i), 1000 + i * 100, 25, "Dev", "e@e.com"));
    }
    auto names = service.getTopTenHighestEarningEmployeeNames();
    EXPECT_EQ(names.size(), 10);
    EXPECT_EQ(names[0], "Emp14");
    EXPECT_EQ(names[9], "Emp5");
}