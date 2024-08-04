package com.hrms.samplehrms.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HRMSException extends RuntimeException  {
    private int code;
    private String message;



}
