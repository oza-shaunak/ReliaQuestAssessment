#include "gtest/gtest.h"
#include "../controller/EmployeeController.hpp"
#include "../service/EmployeeService.hpp"
#include "../model/CreateEmployeeInput.hpp"

TEST(EmployeeControllerTest, CreateAndGetEmployee) {
    EmployeeService service;
    EmployeeController controller(service);

    CreateEmployeeInput input("John Doe", 50000, 30, "Developer", "john@example.com");
    Employee emp = controller.createEmployee(input);

    EXPECT_EQ(emp.name, "John Doe");
    EXPECT_EQ(emp.salary, 50000);
    EXPECT_EQ(emp.age, 30);
    EXPECT_EQ(emp.title, "Developer");
    EXPECT_EQ(emp.email, "john@example.com");

    auto all = controller.getAllEmployees();
    ASSERT_EQ(all.size(), 1);
    EXPECT_EQ(all[0].name, "John Doe");
}

TEST(EmployeeControllerTest, DeleteEmployee) {
    EmployeeService service;
    EmployeeController controller(service);

    CreateEmployeeInput input("Christ Mike", 60000, 28, "Manager", "christ@example.com");
    Employee emp = controller.createEmployee(input);

    ASSERT_EQ(controller.getAllEmployees().size(), 1);
    controller.deleteEmployeeById(emp.id);
    EXPECT_EQ(controller.getAllEmployees().size(), 0);
}