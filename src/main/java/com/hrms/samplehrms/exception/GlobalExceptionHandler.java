package com.hrms.samplehrms.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j

public class GlobalExceptionHandler {
    @ExceptionHandler(HRMSException.class)
    public ResponseEntity<?> handleHRMSException(HRMSException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new HRMSError(e.getMessage(),e.getCode()), HttpStatus.valueOf(e.getCode()));
    }
}
@Data
@AllArgsConstructor
class HRMSError{
    private String message;
    private int code;

}
