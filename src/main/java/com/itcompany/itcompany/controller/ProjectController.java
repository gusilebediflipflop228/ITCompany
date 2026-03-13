package com.itcompany.itcompany.controller;

import com.itcompany.itcompany.dto.ProjectDTO;
import com.itcompany.itcompany.mapper.DTOMapper;
import com.itcompany.itcompany.model.Project;
import com.itcompany.itcompany.model.ProjectMember;
import com.itcompany.itcompany.service.ProjectMemberService;
import com.itcompany.itcompany.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@Tag(name = "Проекты", description = "API для управления проектами")
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;
    private final DTOMapper mapper;

    @GetMapping
    @Operation(summary = "Получить все проекты")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        List<ProjectDTO> dtos = mapper.toProjectDTOList(projects);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить проект по ID")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok(mapper.toProjectDTO(project));
    }

    @PostMapping
    @Operation(summary = "Создать новый проект")
    public ResponseEntity<ProjectDTO> createProject(@Valid @RequestBody ProjectDTO projectDTO) {
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setStatus(projectDTO.getStatus());
        project.setTechStack(projectDTO.getTechStack() != null
                ? String.join(", ", projectDTO.getTechStack())
                : null);
        project.setStartDate(projectDTO.getStartDate());
        project.setDeadline(projectDTO.getDeadline());
        project.setClientName(projectDTO.getClientName());
        project.setClientEmail(projectDTO.getClientEmail());
        project.setClientPhone(projectDTO.getClientPhone());
        project.setLogoUrl(projectDTO.getLogoUrl());

        Project savedProject = projectService.saveProject(project);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toProjectDTO(savedProject));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить существующий проект")
    public ResponseEntity<ProjectDTO> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectDTO projectDTO) {

        Project project = projectService.getProjectById(id);

        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setStatus(projectDTO.getStatus());
        project.setTechStack(projectDTO.getTechStack() != null
                ? String.join(", ", projectDTO.getTechStack())
                : null);
        project.setStartDate(projectDTO.getStartDate());
        project.setDeadline(projectDTO.getDeadline());
        project.setClientName(projectDTO.getClientName());
        project.setClientEmail(projectDTO.getClientEmail());
        project.setClientPhone(projectDTO.getClientPhone());
        project.setLogoUrl(projectDTO.getLogoUrl());

        Project updatedProject = projectService.saveProject(project);
        return ResponseEntity.ok(mapper.toProjectDTO(updatedProject));
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить проект")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/members")
    @Operation(summary = "Добавить участника в проект")
    public ResponseEntity<ProjectMember> addMemberToProject(
            @PathVariable Long id,
            @RequestParam Long employeeId,
            @RequestParam String role) {

        try {
            ProjectMember member = projectMemberService.addMemberToProject(id, employeeId, role);
            return ResponseEntity.status(HttpStatus.CREATED).body(member);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}/members/{employeeId}")
    @Operation(summary = "Удалить участника из проекта")
    public ResponseEntity<Void> removeMemberFromProject(
            @PathVariable Long id,
            @PathVariable Long employeeId) {

        boolean removed = projectMemberService.removeMemberFromProject(id, employeeId);
        return removed
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
