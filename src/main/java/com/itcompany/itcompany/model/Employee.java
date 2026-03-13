package com.itcompany.itcompany.model;

import com.itcompany.itcompany.enums.EmployeePosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String avatarUrl;
    private EmployeePosition position;
}
