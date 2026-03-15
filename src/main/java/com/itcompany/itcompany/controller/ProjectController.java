package com.itcompany.itcompany.controller;

import com.itcompany.itcompany.dto.ProjectDTO;
import com.itcompany.itcompany.enums.ProjectStatus;
import com.itcompany.itcompany.mapper.DTOMapper;
import com.itcompany.itcompany.model.Project;
import com.itcompany.itcompany.model.ProjectMember;
import com.itcompany.itcompany.service.ProjectMemberService;
import com.itcompany.itcompany.service.ProjectService;
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

import java.time.LocalDate;
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
    @Operation(summary = "Получить все проекты (с пагинацией)")
    public ResponseEntity<Page<ProjectDTO>> getAllProjects(
            @PageableDefault(page = 0, size = 10, sort = "name") Pageable pageable) {

        Page<Project> projectsPage = projectService.getAllProjects(pageable);
        Page<ProjectDTO> dtoPage = projectsPage.map(mapper::toProjectDTO);

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/search")
    @Operation(summary = "Поиск проектов по названию, описанию или стеку")
    public ResponseEntity<Page<ProjectDTO>> searchProjects(
            @Parameter(description = "Поисковый запрос", required = true, example = "kotlin")
            @RequestParam String q,

            @PageableDefault(page = 0, size = 10, sort = "name") Pageable pageable) {

        Page<Project> projectsPage = projectService.searchProjects(q, pageable);
        Page<ProjectDTO> dtoPage = projectsPage.map(mapper::toProjectDTO);

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/filter")
    @Operation(summary = "Фильтрация проектов по статусу, технологии и дедлайну")
    public ResponseEntity<Page<ProjectDTO>> filterProjects(
            @Parameter(description = "Статус проекта", example = "ACTIVE")
            @RequestParam(required = false) ProjectStatus status,

            @Parameter(description = "Технология в стеке", example = "Kotlin")
            @RequestParam(required = false) String tech,

            @Parameter(description = "Дедлайн раньше этой даты", example = "2026-06-01")
            @RequestParam(required = false) LocalDate deadlineBefore,

            @PageableDefault(page = 0, size = 10, sort = "name") Pageable pageable) {

        // Если все параметры null — возвращаем все проекты
        Page<Project> projectsPage;
        if (status == null && tech == null && deadlineBefore == null) {
            projectsPage = projectService.getAllProjects(pageable);
        } else {
            projectsPage = projectService.filterProjects(status, tech, deadlineBefore, pageable);
        }

        Page<ProjectDTO> dtoPage = projectsPage.map(mapper::toProjectDTO);
        return ResponseEntity.ok(dtoPage);
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
