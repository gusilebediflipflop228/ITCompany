package com.itcompany.itcompany.service;

import com.itcompany.itcompany.enums.EmployeePosition;
import com.itcompany.itcompany.model.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();

    @PostConstruct
    public void init() {
        employees.add(new Employee(
                1L, "Иван", "Петров", "ivan.petrov@itcompany.ru",
                "https://example.com/avatars/1.jpg", EmployeePosition.BACKEND_DEVELOPER
        ));

        employees.add(new Employee(
                2L, "Анна", "Сидорова", "anna.sidorova@itcompany.ru",
                "https://example.com/avatars/2.jpg", EmployeePosition.FRONTEND_DEVELOPER
        ));

        employees.add(new Employee(
                3L, "Дмитрий", "Козлов", "dmitry.kozlov@itcompany.ru",
                "https://example.com/avatars/3.jpg", EmployeePosition.ANDROID_DEVELOPER
        ));

        employees.add(new Employee(
                4L, "Елена", "Новикова", "elena.novikova@itcompany.ru",
                "https://example.com/avatars/4.jpg", EmployeePosition.QA_ENGINEER
        ));

        employees.add(new Employee(
                5L, "Максим", "Волков", "maxim.volkov@itcompany.ru",
                "https://example.com/avatars/5.jpg", EmployeePosition.PROJECT_MANAGER
        ));

        employees.add(new Employee(
                6L, "Ольга", "Морозова", "olga.morozova@itcompany.ru",
                "https://example.com/avatars/6.jpg", EmployeePosition.IOS_DEVELOPER
        ));

        employees.add(new Employee(
                7L, "Алексей", "Смирнов", "alexey.smirnov@itcompany.ru",
                "https://example.com/avatars/7.jpg", EmployeePosition.DEVOPS
        ));

        employees.add(new Employee(
                8L, "Наталья", "Кузнецова", "natalia.kuznetsova@itcompany.ru",
                "https://example.com/avatars/8.jpg", EmployeePosition.DESIGNER
        ));

        employees.add(new Employee(
                9L, "Сергей", "Попов", "sergey.popov@itcompany.ru",
                "https://example.com/avatars/9.jpg", EmployeePosition.BACKEND_DEVELOPER
        ));

        employees.add(new Employee(
                10L, "Виктория", "Лебедева", "victoria.lebedeva@itcompany.ru",
                "https://example.com/avatars/10.jpg", EmployeePosition.QA_ENGINEER
        ));
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();
    }
}
