package com.itcompany.itcompany.repository;

import com.itcompany.itcompany.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE " +
            "LOWER(e.firstName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(e.lastName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(e.email) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Employee> searchByQuery(@Param("query") String query, Pageable pageable);
}
