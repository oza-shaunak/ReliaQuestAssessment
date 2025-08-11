// ApiExceptions.hpp
#pragma once
#include <stdexcept>
#include <string>

// Exception thrown when an employee with a given ID is not found
class EmployeeNotFoundException : public std::runtime_error {
public:
    explicit EmployeeNotFoundException(const std::string& id)
        : std::runtime_error("Employee not found with id: " + id) {}
};

// Exception thrown when an error occurs with an external service
class ExternalServiceException : public std::runtime_error {
public:
    explicit ExternalServiceException(const std::string& message)
        : std::runtime_error(message) {}
};