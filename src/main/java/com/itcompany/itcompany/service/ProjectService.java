package com.itcompany.itcompany.service;

import com.itcompany.itcompany.enums.ProjectStatus;
import com.itcompany.itcompany.model.Project;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private List<Project> projects = new ArrayList<>();

    @PostConstruct
    public void init() {
        projects.add(new Project(
                1L, "Интернет-магазин", "Разработка платформы электронной коммерции",
                ProjectStatus.ACTIVE, List.of("Java", "Spring Boot", "PostgreSQL", "React"),
                LocalDate.of(2025, 1, 15), LocalDate.of(2025, 6, 30),
                "ООО Торговый Дом", "info@tradinghouse.ru", "+7 (495) 123-45-67",
                "https://example.com/logos/project1.png"
        ));

        projects.add(new Project(
                2L, "Корпоративный портал", "Внутренний портал для сотрудников",
                ProjectStatus.PLANNING, List.of("Java", "Spring Boot", "Angular"),
                LocalDate.of(2025, 3, 1), LocalDate.of(2025, 9, 30),
                "АО Технопарк", "contact@technopark.ru", "+7 (812) 987-65-43",
                "https://example.com/logos/project2.png"
        ));

        projects.add(new Project(
                3L, "Мобильное приложение", "Приложение для доставки еды",
                ProjectStatus.COMPLETED, List.of("Kotlin", "Android", "Firebase"),
                LocalDate.of(2024, 10, 1), LocalDate.of(2025, 2, 28),
                "ИП Иванов", "ivanov@mail.ru", "+7 (999) 111-22-33",
                "https://example.com/logos/project3.png"
        ));

        projects.add(new Project(
                4L, "CRM система", "Система управления клиентами",
                ProjectStatus.ACTIVE, List.of("Python", "Django", "PostgreSQL"),
                LocalDate.of(2025, 2, 1), LocalDate.of(2025, 8, 31),
                "ЗАО Ритейл Групп", "crm@retailgroup.ru", "+7 (495) 555-12-34",
                "https://example.com/logos/project4.png"
        ));

        projects.add(new Project(
                5L, "Сайт визитка", "Лендинг для стартапа",
                ProjectStatus.ON_HOLD, List.of("HTML", "CSS", "JavaScript"),
                LocalDate.of(2025, 1, 10), LocalDate.of(2025, 3, 15),
                "ИП Смирнов", "smirnov@startup.io", "+7 (999) 888-77-66",
                "https://example.com/logos/project5.png"
        ));
    }

    public List<Project> getAllProjects() {
        return new ArrayList<>(projects);
    }

    public Optional<Project> getProjectById(Long id) {
        return projects.stream()
                .filter(project -> project.getId().equals(id))
                .findFirst();
    }
}
