package com.itcompany.itcompany.controller;

import com.itcompany.itcompany.dto.EmployeeDTO;
import com.itcompany.itcompany.mapper.DTOMapper;
import com.itcompany.itcompany.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Tag(name = "Сотрудники", description = "API для управления сотрудниками")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final DTOMapper dtoMapper;

    @GetMapping
    @Operation(summary = "Получить всех сотрудников")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(dtoMapper.toEmployeeDTOList(employeeService.getAllEmployees()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить сотрудника по ID")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(dtoMapper::toEmployeeDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
