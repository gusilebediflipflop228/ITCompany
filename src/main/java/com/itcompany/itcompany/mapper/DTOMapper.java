package com.itcompany.itcompany.mapper;

import com.itcompany.itcompany.dto.EmployeeDTO;
import com.itcompany.itcompany.dto.ProjectDTO;
import com.itcompany.itcompany.model.Employee;
import com.itcompany.itcompany.model.Project;
import com.itcompany.itcompany.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DTOMapper {
    private final ProjectMemberService projectMemberService;

    public ProjectDTO toProjectDTO(Project project) {
        List<String> teamMemberNames = projectMemberService.getProjectMemberNamesByProjectId(project.getId());

        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setStatus(project.getStatus());
        dto.setTechStack(project.getTechStack());
        dto.setStartDate(project.getStartDate());
        dto.setDeadline(project.getDeadline());
        dto.setClientName(project.getClientName());
        dto.setClientEmail(project.getClientEmail());
        dto.setClientPhone(project.getClientPhone());
        dto.setLogoUrl(project.getLogoUrl());
        dto.setTeamMemberNames(teamMemberNames);
        return dto;
    }

    public List<ProjectDTO> toProjectDTOList(List<Project> projects) {
        return projects.stream()
                .map(this::toProjectDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO toEmployeeDTO(Employee employee) {
        List<String> projectNames = projectMemberService.getProjectNamesByEmployeeId(employee.getId());

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setAvatarUrl(employee.getAvatarUrl());
        dto.setPosition(employee.getPosition());
        dto.setProjectNames(projectNames);

        return dto;
    }

    public List<EmployeeDTO> toEmployeeDTOList(List<Employee> employees) {
        return employees.stream()
                .map(this::toEmployeeDTO)
                .collect(Collectors.toList());
    }
}
