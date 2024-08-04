package com.hrms.samplehrms.service;

import com.hrms.samplehrms.domain.EmployeeRequest;
import com.hrms.samplehrms.domain.EmployeeResponse;
import com.hrms.samplehrms.exception.HRMSException;
import com.hrms.samplehrms.model.Department;
import com.hrms.samplehrms.model.Employee;
import com.hrms.samplehrms.repository.DepartmentRepository;
import com.hrms.samplehrms.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class HRMSService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> all = employeeRepository.findAll();
        log.info("Calling all employees");
        return all.stream().map(this::adaptToDTO).collect(Collectors.toList());

    }

    public EmployeeResponse getEmployeeById(Integer id) {
        Optional<Employee> byId = employeeRepository.findById(id);
        log.info("Starting getEmployeeById");
        return byId.map(this::adaptToDTO).orElseThrow(()->new HRMSException( HttpStatus.NOT_FOUND.value(),String.format("Employee with id %s not found", id)));
    }

    public EmployeeResponse addEmployee(EmployeeRequest employee) {
        if (Objects.isNull(employee))
        {
            log.error("EmployeeRequest is null");
            throw new HRMSException( HttpStatus.BAD_REQUEST.value(),"Employee cannot be empty");
        }
        if (Objects.isNull(employee.getDepartmentName()) || employee.getDepartmentName().isBlank())
        {
            log.error("DepartmentName is null or empty");
            throw new HRMSException( HttpStatus.BAD_REQUEST.value(),"Department cannot be empty");
        }

        Department department = departmentRepository.findByDepartmentNameIgnoreCase(employee.getDepartmentName());
        if (Objects.isNull(department) ){
            log.error("Department not found");
            throw new HRMSException(HttpStatus.BAD_REQUEST.value(),String.format("Department %s not found", employee.getDepartmentName()));
        }

       Employee exists = employeeRepository.findByEmailAddressIgnoreCase(employee.getEmailAddress());
        if (Objects.nonNull(exists)){
            log.error("Employee already exists");
            throw new HRMSException(HttpStatus.CONFLICT.value(),String.format("Employee with email address %s already exists", employee.getEmailAddress()));
        }
        Employee newEmployee = new Employee();
        newEmployee.setEmailAddress(employee.getEmailAddress());
        newEmployee.setDepartment(department);
        newEmployee.setFirstName(employee.getFirstName());
        newEmployee.setLastName(employee.getLastName());

        Employee save = employeeRepository.save(newEmployee);
        log.info("Employee saved");
        return adaptToDTO(save);


    }

    private EmployeeResponse adaptToDTO(Employee employee) {
        return EmployeeResponse.builder()
                .employeeId(employee.getEmployeeId())
                .departmentName(employee.getDepartment().getDepartmentName())
                .lastName(employee.getLastName())
                .firstName(employee.getFirstName())
                .emailAddress(employee.getEmailAddress())
                .build();
    }


}
