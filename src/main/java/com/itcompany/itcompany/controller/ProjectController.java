package com.itcompany.itcompany.controller;
import com.itcompany.itcompany.dto.ProjectDTO;
import com.itcompany.itcompany.mapper.DTOMapper;
import com.itcompany.itcompany.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
// чисто для коммитаgit branch
@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@Tag(name = "Проекты", description = "API для управления проектами")
public class ProjectController {
    private final ProjectService projectService;
    private final DTOMapper dtoMapper;

    @GetMapping
    @Operation(summary = "Получить все проекты")
    public ResponseEntity<List<ProjectDTO>> getAllProjects(){
        return ResponseEntity.ok(dtoMapper.toProjectDTOList(projectService.getAllProjects()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить проект по ID")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id){
        return projectService.getProjectById(id)
                .map(dtoMapper::toProjectDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
