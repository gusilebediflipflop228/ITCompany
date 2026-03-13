package com.itcompany.itcompany.model;

import com.itcompany.itcompany.enums.ProjectRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMember {
    private Long id;
    private Project project;
    private Employee employee;
    private ProjectRole role;
}
