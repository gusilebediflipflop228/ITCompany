IT Company API

REST API для управления проектами и сотрудниками IT-компании. Поддерживает пагинацию, поиск, фильтрацию, валидацию и безопасность.

# Стек технологий

| Категория | Технологии |
| **Язык** | Java 17 |
| **Фреймворк** | Spring Boot 3.5.11 |
| **База данных** | PostgreSQL 16 |
| **ORM** | Spring Data JPA + Hibernate |
| **Миграции** | Flyway 11.7.2 |
| **Безопасность** | Spring Security (Basic Auth) |
| **Документация** | Swagger UI (springdoc 2.8.3) |
| **Мониторинг** | Spring Boot Actuator |
| **Контейнеризация** | Docker + docker-compose |
| **Сборка** | Maven |
| **Утилиты** | Lombok, Validation |
 
## Как запустить локально
## Требования
- Java 17+
- Maven 3.8+
- PostgreSQL 16

### Шаг 1: Настрой базу данных

```sql
CREATE DATABASE project_manager;
```
### Шаг 2: Настрой подключение

Перейди в src/main/resources/application.yml и укажи свои учётные данные:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/project_manager
    username: postgres
    password: postgres
```

### Шаг 3: Запусти приложение

```bash
# Собери проект
.\mvnw.cmd clean package -DskipTests

# Запусти приложение
.\mvnw.cmd spring-boot:run
```

### Шаг 4: Доступ к API
Swagger UI: http://localhost:8080/swagger-ui/index.html
Actuator Health: http://localhost:8080/actuator/health
API Base URL: http://localhost:8080/api

## Запуск через Docker
## Требования
- Docker
- Docker Compose

### Шаг 1: Собери проект

```bash
.\mvnw.cmd clean package -DskipTests
```

### Шаг 2: Запусти контейнеры

```bash
# Одна команда поднимает и БД, и приложение
docker-compose up -d
```

### Шаг 3: Проверь статус

```bash
docker-compose ps

# Логи приложения
docker compose logs -f app
```

### Шаг 4: Остановка

```bash
docker-compose down
```

## API Endpoints
### Проекты
- `POST /api/projects` - Создать проект
- `GET /api/projects` - Получить список проектов (с пагинацией, фильтрацией, поиском)
- `GET /api/projects/{id}` - Получить проект по ID
- `PUT /api/projects/{id}` - Обновить проект
- `DELETE /api/projects/{id}` - Удалить проект

### Сотрудники
- `POST /api/employees` - Создать сотрудника
- `GET /api/employees` - Получить список сотрудников (с пагинацией, фильтрацией, поиском)
- `GET /api/employees/{id}` - Получить сотрудника по ID
- `PUT /api/employees/{id}` - Обновить сотрудника
- `DELETE /api/employees/{id}` - Удалить сотрудника

## Безопасность
- Все эндпоинты защищены Basic Auth.
- Дефолтные креды: `admin:password`
- Рекомендуется изменить их в production.
- Роли и права доступа можно расширить при необходимости.
- Для тестирования можно использовать Postman или curl с указанием Basic Auth.
- Пример curl запроса:

```bash
curl -u admin:admin123 -X POST http://localhost:8080/api/projects \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Новый проект",
    "description": "Описание проекта",
    "status": "ACTIVE",
    "techStack": ["Java", "Spring Boot", "PostgreSQL"],
    "startDate": "2026-03-15",
    "deadline": "2026-12-31",
    "clientName": "Клиент ООО",
    "clientEmail": "client@example.com"
  }'
```

## Мониторинг и метрики
- Spring Boot Actuator предоставляет метрики и информацию о состоянии приложения.
- Доступ к метрикам: http://localhost:8080/actuator/metrics
- Доступ к health check: http://localhost:8080/actuator/health

## Документация
- Swagger UI доступен по адресу: http://localhost:8080/swagger-ui/index.html
- Здесь можно ознакомиться с полным описанием API, параметрами запросов и примерами ответов.
- Рекомендуется использовать Swagger для тестирования и ознакомления с API, особенно при разработке фронтенда или интеграции с другими сервисами.
- Swagger UI автоматически обновляется при изменении кода, что обеспечивает актуальную документацию для разработчиков.

## Структура проекта
- `src/main/java/com/itcompany` - Основной код приложения
  - `controller` - REST контроллеры для обработки HTTP запросов
  - `dto` - Data Transfer Objects для передачи данных между слоями
  - `enums` - Перечисления для статусов и других констант
  - `exception` - Классы для обработки исключений и ошибок
  - `mapper` - MapStruct мапперы для преобразования между сущностями и DTO
  - `repository` - Репозитории для доступа к данным
  - `model` - Сущности для данных
  - `config` - Конфигурационные классы для безопасности, Swagger и других аспект
  - `service` - Сервисный слой для бизнес-логики\
- `src/main/resources` - Ресурсы приложения
- `src/test/java/com/itcompany` - Тесты для приложения
- `src/main/resources/application.yml` - Конфигурация приложения
- `src/main/resources/db/migration` - SQL скрипты для миграций Flyway
    - `Dockerfile` - Файл для сборки Docker образа
    - `docker-compose.yml` - Файл для запуска приложения и базы данных в Docker
    - `pom.xml` - Файл конфигурации Maven для управления зависимостями и сборкой проекта
    - `README.md` - Документация и инструкции по запуску и использованию приложения

## Контакты
- Разработчик: Юрисаров Тимофей