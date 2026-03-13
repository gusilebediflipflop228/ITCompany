package com.itcompany.itcompany.service;

import com.itcompany.itcompany.enums.EmployeePosition;
import com.itcompany.itcompany.exception.NotFoundException;
import com.itcompany.itcompany.model.Employee;
import com.itcompany.itcompany.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Сотрудник", id));
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new NotFoundException("Сотрудник", id);
        }
        employeeRepository.deleteById(id);
    }
}
