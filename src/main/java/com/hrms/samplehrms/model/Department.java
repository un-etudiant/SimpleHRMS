package com.hrms.samplehrms.model;
import  jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="department")
public class Department {
    @Id
    @Column(insertable = false,updatable = false,name="dept_id")
    private Integer deptId;

    @Column(insertable = false,updatable = false,name="department_name")
    private String departmentName;

}
