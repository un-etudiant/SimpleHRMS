package com.hrms.samplehrms.repository;

import com.hrms.samplehrms.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    Employee findByEmailAddress(String emailAddress);

    Employee findByEmailAddressIgnoreCase(String emailAddress);
}
