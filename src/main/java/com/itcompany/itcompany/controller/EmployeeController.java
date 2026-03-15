package com.itcompany.itcompany.controller;

import com.itcompany.itcompany.dto.EmployeeDTO;
import com.itcompany.itcompany.mapper.DTOMapper;
import com.itcompany.itcompany.model.Employee;
import com.itcompany.itcompany.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Tag(name = "Сотрудники", description = "API для управления сотрудниками")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DTOMapper mapper;

    @GetMapping
    @Operation(summary = "Получить всех сотрудников (с пагинацией)")
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployees(
            @PageableDefault(page = 0, size = 10, sort = "lastName") Pageable pageable) {

        Page<Employee> employeesPage = employeeService.getAllEmployees(pageable);
        Page<EmployeeDTO> dtoPage = employeesPage.map(mapper::toEmployeeDTO);

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/search")
    @Operation(summary = "Поиск сотрудников по имени, фамилии или email")
    public ResponseEntity<Page<EmployeeDTO>> searchEmployees(
            @Parameter(description = "Поисковый запрос", required = true, example = "Иванов")
            @RequestParam String q,

            @PageableDefault(page = 0, size = 10, sort = "lastName") Pageable pageable) {

        Page<Employee> employeesPage = employeeService.searchEmployees(q, pageable);
        Page<EmployeeDTO> dtoPage = employeesPage.map(mapper::toEmployeeDTO);

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить сотрудника по ID")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(mapper.toEmployeeDTO(employee));
    }

    @PostMapping
    @Operation(summary = "Создать нового сотрудника")
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPosition(employeeDTO.getPosition());
        employee.setAvatarUrl(employeeDTO.getAvatarUrl());

        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toEmployeeDTO(savedEmployee));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить сотрудника")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeDTO employeeDTO) {

        Employee employee = employeeService.getEmployeeById(id);

        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPosition(employeeDTO.getPosition());
        employee.setAvatarUrl(employeeDTO.getAvatarUrl());

        Employee updatedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(mapper.toEmployeeDTO(updatedEmployee));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить сотрудника")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
