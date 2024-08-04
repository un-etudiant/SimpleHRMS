package com.hrms.samplehrms.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentDTO {
    private Integer departmentId;
    private String departmentName;
}
