package com.itcompany.itcompany.service;

import com.itcompany.itcompany.enums.ProjectStatus;
import com.itcompany.itcompany.exception.NotFoundException;
import com.itcompany.itcompany.model.Project;
import com.itcompany.itcompany.repository.ProjectRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public Page<Project> searchProjects(String query, Pageable pageable) {
        return projectRepository.searchByQuery(query, pageable);
    }

    public Page<Project> filterByStatus(ProjectStatus status, Pageable pageable) {
        return projectRepository.findByStatus(status, pageable);
    }

    public Page<Project> filterByTech(String tech, Pageable pageable) {
        return projectRepository.findByTechStackContaining(tech, pageable);
    }

    public Page<Project> filterByDeadlineBefore(LocalDate deadline, Pageable pageable) {
        return projectRepository.findByDeadlineBefore(deadline, pageable);
    }

    public Page<Project> filterProjects(
            ProjectStatus status,
            String tech,
            LocalDate deadlineBefore,
            Pageable pageable) {
        return projectRepository.filterProjects(status, tech, deadlineBefore, pageable);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Проект", id));
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new NotFoundException("Проект", id);
        }
        projectRepository.deleteById(id);
    }
}
