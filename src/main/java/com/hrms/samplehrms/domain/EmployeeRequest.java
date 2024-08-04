package com.hrms.samplehrms.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class EmployeeRequest {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String departmentName;
}
