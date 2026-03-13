package com.itcompany.itcompany.dto;

import com.itcompany.itcompany.enums.EmployeePosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String avatarUrl;
    private EmployeePosition position;

    private List<String> projectNames;
}
