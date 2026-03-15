package com.itcompany.itcompany.repository;

import com.itcompany.itcompany.enums.ProjectStatus;
import com.itcompany.itcompany.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.techStack) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Project> searchByQuery(@Param("query") String query, Pageable pageable);


    Page<Project> findByStatus(ProjectStatus status, Pageable pageable);

    @Query("SELECT p FROM Project p WHERE LOWER(p.techStack) LIKE LOWER(CONCAT('%', :tech, '%'))")
    Page<Project> findByTechStackContaining(@Param("tech") String tech, Pageable pageable);

    Page<Project> findByDeadlineBefore(LocalDate deadline, Pageable pageable);

    @Query("SELECT p FROM Project p WHERE " +
            "(:status IS NULL OR p.status = :status) AND " +
            "(:tech IS NULL OR LOWER(p.techStack) LIKE LOWER(CONCAT('%', :tech, '%'))) AND " +
            "(:deadlineBefore IS NULL OR p.deadline < :deadlineBefore)")
    Page<Project> filterProjects(
            @Param("status") ProjectStatus status,
            @Param("tech") String tech,
            @Param("deadlineBefore") LocalDate deadlineBefore,
            Pageable pageable);
}