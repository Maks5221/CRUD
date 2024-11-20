package com.mkdev.crud.mapper;

import com.mkdev.crud.dto.EmployeeDto;
import com.mkdev.crud.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.id(),
                employeeDto.firstName(),
                employeeDto.lastName(),
                employeeDto.email()
        );
    }
}
