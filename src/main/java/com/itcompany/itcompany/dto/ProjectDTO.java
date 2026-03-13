package com.itcompany.itcompany.dto;

import com.itcompany.itcompany.enums.ProjectStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private Long id;

    @NotBlank(message = "Название проекта обязательно")
    @Size(min = 3, max = 100, message = "Название должно содержать от 3 до 100 символов")
    private String name;

    @Size(max = 500, message = "Описание не должно превышать 500 символов")
    private String description;

    @NotNull(message = "Статус проекта обязателен")
    private ProjectStatus status;

    private List<String> techStack;

    @PastOrPresent(message = "Дата начала не может быть в будущем")
    private LocalDate startDate;

    @FutureOrPresent(message = "Дедлайн не может быть в прошлом")
    private LocalDate deadline;

    @NotBlank(message = "Имя клиента обязательно")
    @Size(min = 2, max = 100, message = "Имя клиента должно содержать от 2 до 100 символов")
    private String clientName;

    @Email(message = "Неверный формат email")
    private String clientEmail;

    @Pattern(regexp = "^\\+?[0-9\\s\\-()]{10,20}$", message = "Неверный формат телефона")
    private String clientPhone;

    private String logoUrl;

    private List<String> teamMemberNames;
}

