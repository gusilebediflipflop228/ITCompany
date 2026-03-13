CREATE TABLE projects (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          description VARCHAR(500),
                          status VARCHAR(50) NOT NULL,
                          tech_stack VARCHAR(500),
                          start_date DATE,
                          deadline DATE,
                          client_name VARCHAR(100) NOT NULL,
                          client_email VARCHAR(100),
                          client_phone VARCHAR(50),
                          logo_url VARCHAR(255),

                          CONSTRAINT chk_project_name_min_length CHECK (LENGTH(name) >= 3),
                          CONSTRAINT chk_client_name_min_length CHECK (LENGTH(client_name) >= 2)
);

CREATE TABLE employees (
                           id BIGSERIAL PRIMARY KEY,
                           first_name VARCHAR(50) NOT NULL,
                           last_name VARCHAR(50) NOT NULL,
                           email VARCHAR(100) NOT NULL UNIQUE,
                           avatar_url VARCHAR(255),
                           position VARCHAR(50) NOT NULL,

                           CONSTRAINT chk_email_format CHECK (email LIKE '%@%.%')
);

CREATE TABLE project_members (
                                 id BIGSERIAL PRIMARY KEY,
                                 project_id BIGINT NOT NULL,
                                 employee_id BIGINT NOT NULL,
                                 role VARCHAR(50) NOT NULL,

                                 CONSTRAINT fk_project_members_project
                                     FOREIGN KEY (project_id)
                                         REFERENCES projects(id)
                                         ON DELETE CASCADE,

                                 CONSTRAINT fk_project_members_employee
                                     FOREIGN KEY (employee_id)
                                         REFERENCES employees(id)
                                         ON DELETE CASCADE,

                                 CONSTRAINT uk_project_member UNIQUE (project_id, employee_id)
);

CREATE INDEX idx_projects_status ON projects(status);
CREATE INDEX idx_projects_client_name ON projects(client_name);
CREATE INDEX idx_employees_email ON employees(email);
CREATE INDEX idx_employees_position ON employees(position);
CREATE INDEX idx_project_members_project ON project_members(project_id);
CREATE INDEX idx_project_members_employee ON project_members(employee_id);
CREATE INDEX idx_project_members_role ON project_members(role);

COMMENT ON TABLE projects IS 'Проекты IT-компании';
COMMENT ON TABLE employees IS 'Сотрудники компании';
COMMENT ON TABLE project_members IS 'Связь сотрудников с проектами (роли в проектах)';