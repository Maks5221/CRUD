package com.mkdev.crud.service;

import com.mkdev.crud.dto.EmployeeDto;
import com.mkdev.crud.entity.Employee;
import com.mkdev.crud.exception.ResourceNotFoundException;
import com.mkdev.crud.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import com.mkdev.crud.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        var employee = EmployeeMapper.mapToEmployee(employeeDto);
        var savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        var employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given ID: " + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream().map(EmployeeMapper::mapToEmployeeDto)
                .toList();
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        var employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given ID: " + employeeId));

        employee.setFirstName(updatedEmployee.firstName());
        employee.setLastName(updatedEmployee.lastName());
        employee.setEmail(updatedEmployee.email());

        employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        var employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given ID: " + employeeId));

        employeeRepository.deleteById(employeeId);
    }

}
