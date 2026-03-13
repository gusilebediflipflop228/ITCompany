package com.itcompany.itcompany.service;

import com.itcompany.itcompany.enums.ProjectRole;
import com.itcompany.itcompany.model.Employee;
import com.itcompany.itcompany.model.Project;
import com.itcompany.itcompany.model.ProjectMember;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProjectMemberService {
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    private List<ProjectMember> projectMembers = new ArrayList<>();

    @PostConstruct
    public void init() {
        List<Project> projects = projectService.getAllProjects();
        List<Employee> employees = employeeService.getAllEmployees();

        projectMembers.add(new ProjectMember(1L, projects.get(0), employees.get(0), ProjectRole.LEAD));
        projectMembers.add(new ProjectMember(2L, projects.get(0), employees.get(1), ProjectRole.DEVELOPER));
        projectMembers.add(new ProjectMember(3L, projects.get(0), employees.get(3), ProjectRole.QA));
        projectMembers.add(new ProjectMember(4L, projects.get(0), employees.get(7), ProjectRole.DESIGNER));

        projectMembers.add(new ProjectMember(5L, projects.get(1), employees.get(4), ProjectRole.MANAGER));
        projectMembers.add(new ProjectMember(6L, projects.get(1), employees.get(0), ProjectRole.DEVELOPER));
        projectMembers.add(new ProjectMember(7L, projects.get(1), employees.get(8), ProjectRole.DEVELOPER));

        projectMembers.add(new ProjectMember(8L, projects.get(2), employees.get(2), ProjectRole.LEAD));
        projectMembers.add(new ProjectMember(9L, projects.get(2), employees.get(5), ProjectRole.DEVELOPER));
        projectMembers.add(new ProjectMember(10L, projects.get(2), employees.get(3), ProjectRole.QA));

        projectMembers.add(new ProjectMember(11L, projects.get(3), employees.get(8), ProjectRole.LEAD));
        projectMembers.add(new ProjectMember(12L, projects.get(3), employees.get(0), ProjectRole.DEVELOPER));
        projectMembers.add(new ProjectMember(13L, projects.get(3), employees.get(9), ProjectRole.QA));

        projectMembers.add(new ProjectMember(14L, projects.get(4), employees.get(7), ProjectRole.DESIGNER));
        projectMembers.add(new ProjectMember(15L, projects.get(4), employees.get(1), ProjectRole.DEVELOPER));
    }

    public List<String> getProjectMemberNamesByProjectId(Long projectId) {
        return projectMembers.stream()
                .filter(pm -> pm.getProject().getId().equals(projectId))
                .map(pm -> pm.getEmployee().getFirstName() + " " + pm.getEmployee().getLastName())
                .collect(Collectors.toList());
    }

    public List<String> getProjectNamesByEmployeeId(Long employeeId) {
        return projectMembers.stream()
                .filter(pm -> pm.getEmployee().getId().equals(employeeId))
                .map(pm -> pm.getProject().getName())
                .collect(Collectors.toList());
    }
}
