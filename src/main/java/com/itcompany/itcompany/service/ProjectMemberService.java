package com.itcompany.itcompany.service;

import com.itcompany.itcompany.enums.ProjectRole;
import com.itcompany.itcompany.model.Project;
import com.itcompany.itcompany.model.ProjectMember;
import com.itcompany.itcompany.repository.ProjectMemberRepository;
import com.itcompany.itcompany.repository.ProjectRepository;
import com.itcompany.itcompany.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;


    public List<String> getTeamMemberNamesByProjectId(Long projectId) {
        return projectMemberRepository.findByProjectId(projectId).stream()
                .map(pm -> pm.getEmployee().getFirstName() + " " + pm.getEmployee().getLastName())
                .collect(Collectors.toList());
    }

    public List<String> getProjectNamesByEmployeeId(Long employeeId) {
        return projectMemberRepository.findByEmployeeId(employeeId).stream()
                .map(pm -> pm.getProject().getName())
                .collect(Collectors.toList());
    }


    public ProjectMember addMemberToProject(Long projectId, Long employeeId, String role) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        var employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        boolean alreadyMember = projectMemberRepository.findByProjectId(projectId).stream()
                .anyMatch(pm -> pm.getEmployee().getId().equals(employeeId));

        if (alreadyMember) {
            throw new IllegalArgumentException("Employee already in project");
        }

        ProjectRole projectRole = ProjectRole.valueOf(role.toUpperCase());

        ProjectMember member = ProjectMember.builder()
                .project(project)
                .employee(employee)
                .role(projectRole)
                .build();

        return projectMemberRepository.save(member);
    }

    public boolean removeMemberFromProject(Long projectId, Long employeeId) {
        List<ProjectMember> members = projectMemberRepository.findByProjectId(projectId);

        var memberToRemove = members.stream()
                .filter(pm -> pm.getEmployee().getId().equals(employeeId))
                .findFirst();

        if (memberToRemove.isPresent()) {
            projectMemberRepository.delete(memberToRemove.get());
            return true;
        }

        return false;
    }
}
