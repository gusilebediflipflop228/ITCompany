
INSERT INTO employees (id, first_name, last_name, email, avatar_url, position) VALUES
                                                                                   (1, 'Иван', 'Петров', 'ivan.petrov@itcompany.ru', 'https://example.com/avatars/1.jpg', 'BACKEND_DEVELOPER'),
                                                                                   (2, 'Анна', 'Сидорова', 'anna.sidorova@itcompany.ru', 'https://example.com/avatars/2.jpg', 'FRONTEND_DEVELOPER'),
                                                                                   (3, 'Дмитрий', 'Козлов', 'dmitry.kozlov@itcompany.ru', 'https://example.com/avatars/3.jpg', 'ANDROID_DEVELOPER'),
                                                                                   (4, 'Елена', 'Новикова', 'elena.novikova@itcompany.ru', 'https://example.com/avatars/4.jpg', 'QA_ENGINEER'),
                                                                                   (5, 'Максим', 'Волков', 'maxim.volkov@itcompany.ru', 'https://example.com/avatars/5.jpg', 'PROJECT_MANAGER'),
                                                                                   (6, 'Ольга', 'Морозова', 'olga.morozova@itcompany.ru', 'https://example.com/avatars/6.jpg', 'IOS_DEVELOPER'),
                                                                                   (7, 'Алексей', 'Смирнов', 'alexey.smirnov@itcompany.ru', 'https://example.com/avatars/7.jpg', 'DEVOPS'),
                                                                                   (8, 'Наталья', 'Кузнецова', 'natalia.kuznetsova@itcompany.ru', 'https://example.com/avatars/8.jpg', 'DESIGNER'),
                                                                                   (9, 'Сергей', 'Попов', 'sergey.popov@itcompany.ru', 'https://example.com/avatars/9.jpg', 'BACKEND_DEVELOPER'),
                                                                                   (10, 'Виктория', 'Лебедева', 'victoria.lebedeva@itcompany.ru', 'https://example.com/avatars/10.jpg', 'QA_ENGINEER');

SELECT setval('employees_id_seq', 10);

INSERT INTO projects (id, name, description, status, tech_stack, start_date, deadline, client_name, client_email, client_phone, logo_url) VALUES
                                                                                                                                              (1,
                                                                                                                                               'Интернет-магазин',
                                                                                                                                               'Разработка платформы электронной коммерции',
                                                                                                                                               'ACTIVE',
                                                                                                                                               'Java, Spring Boot, PostgreSQL, React',
                                                                                                                                               '2025-01-15',
                                                                                                                                               '2025-06-30',
                                                                                                                                               'ООО Торговый Дом',
                                                                                                                                               'info@tradinghouse.ru',
                                                                                                                                               '+7 (495) 123-45-67',
                                                                                                                                               'https://example.com/logos/project1.png'),

                                                                                                                                              (2,
                                                                                                                                               'Корпоративный портал',
                                                                                                                                               'Внутренний портал для сотрудников компании',
                                                                                                                                               'PLANNING',
                                                                                                                                               'Java, Spring Boot, Angular',
                                                                                                                                               '2025-03-01',
                                                                                                                                               '2025-09-30',
                                                                                                                                               'АО Технопарк',
                                                                                                                                               'contact@technopark.ru',
                                                                                                                                               '+7 (812) 987-65-43',
                                                                                                                                               'https://example.com/logos/project2.png'),

                                                                                                                                              (3,
                                                                                                                                               'Мобильное приложение',
                                                                                                                                               'Приложение для доставки еды',
                                                                                                                                               'COMPLETED',
                                                                                                                                               'Kotlin, Android, Firebase',
                                                                                                                                               '2024-10-01',
                                                                                                                                               '2025-02-28',
                                                                                                                                               'ИП Иванов',
                                                                                                                                               'ivanov@mail.ru',
                                                                                                                                               '+7 (999) 111-22-33',
                                                                                                                                               'https://example.com/logos/project3.png'),

                                                                                                                                              (4,
                                                                                                                                               'CRM система',
                                                                                                                                               'Система управления клиентами для ритейла',
                                                                                                                                               'ACTIVE',
                                                                                                                                               'Python, Django, PostgreSQL',
                                                                                                                                               '2025-02-01',
                                                                                                                                               '2025-08-31',
                                                                                                                                               'ЗАО Ритейл Групп',
                                                                                                                                               'crm@retailgroup.ru',
                                                                                                                                               '+7 (495) 555-12-34',
                                                                                                                                               'https://example.com/logos/project4.png'),

                                                                                                                                              (5,
                                                                                                                                               'Сайт-визитка',
                                                                                                                                               'Лендинг для стартапа',
                                                                                                                                               'ON_HOLD',
                                                                                                                                               'HTML, CSS, JavaScript',
                                                                                                                                               '2025-01-10',
                                                                                                                                               '2025-03-15',
                                                                                                                                               'ИП Смирнов',
                                                                                                                                               'smirnov@startup.io',
                                                                                                                                               '+7 (999) 888-77-66',
                                                                                                                                               'https://example.com/logos/project5.png');

SELECT setval('projects_id_seq', 5);

INSERT INTO project_members (project_id, employee_id, role) VALUES
-- Проект 1: Интернет-магазин (4 участника)
(1, 1, 'LEAD'),           -- Иван Петров - Team Lead
(1, 2, 'DEVELOPER'),      -- Анна Сидорова - Frontend Developer
(1, 4, 'QA'),             -- Елена Новикова - QA Engineer
(1, 8, 'DESIGNER'),       -- Наталья Кузнецова - Designer

-- Проект 2: Корпоративный портал (3 участника)
(2, 5, 'MANAGER'),        -- Максим Волков - Project Manager
(2, 1, 'DEVELOPER'),      -- Иван Петров - Backend Developer
(2, 9, 'DEVELOPER'),      -- Сергей Попов - Backend Developer

-- Проект 3: Мобильное приложение (3 участника)
(3, 3, 'LEAD'),           -- Дмитрий Козлов - Android Lead
(3, 6, 'DEVELOPER'),      -- Ольга Морозова - iOS Developer
(3, 4, 'QA'),             -- Елена Новикова - QA Engineer

-- Проект 4: CRM система (3 участника)
(4, 9, 'LEAD'),           -- Сергей Попов - Backend Lead
(4, 1, 'DEVELOPER'),      -- Иван Петров - Backend Developer
(4, 10, 'QA'),            -- Виктория Лебедева - QA Engineer

-- Проект 5: Сайт-визитка (2 участника)
(5, 8, 'DESIGNER'),       -- Наталья Кузнецова - Designer
(5, 2, 'DEVELOPER');      -- Анна Сидорова - Frontend Developer

SELECT setval('project_members_id_seq', 15);