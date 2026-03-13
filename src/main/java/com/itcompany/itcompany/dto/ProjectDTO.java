package com.itcompany.itcompany.dto;

import com.itcompany.itcompany.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private ProjectStatus status;
    private List<String> techStack;
    private LocalDate startDate;
    private LocalDate deadline;
    private String clientName;
    private String clientEmail;
    private String clientPhone;
    private String logoUrl;

    private List<String> teamMemberNames;
}
