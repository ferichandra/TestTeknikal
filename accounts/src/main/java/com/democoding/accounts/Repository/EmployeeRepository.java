package com.democoding.accounts.Repository;

import com.democoding.accounts.Entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    @Query("select a from Employee a where lower(a.name) like %:name% and  a.deleteAt is null ")
    Page<Employee> findAll(Pageable page, @Param("name")String name);
    Employee findEmployeeByIdAndDeleteAtIsNull(Long id);
    @Query("Select AVG(a.age) as average from Employee a where a.deleteAt is null")
    Float avgEmployee();
}
