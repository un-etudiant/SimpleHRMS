package com.hrms.samplehrms.controller;

import com.hrms.samplehrms.domain.EmployeeRequest;
import com.hrms.samplehrms.domain.EmployeeResponse;
import com.hrms.samplehrms.service.HRMSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/hrms")
@RequiredArgsConstructor
public class HRMSController {
    private final HRMSService hrmsService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Integer id) {
        return new ResponseEntity<>(hrmsService.getEmployeeById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return new ResponseEntity<>(hrmsService.getAllEmployees(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employee) {
        return new ResponseEntity<>(hrmsService.addEmployee(employee), HttpStatus.CREATED);

    }



}

