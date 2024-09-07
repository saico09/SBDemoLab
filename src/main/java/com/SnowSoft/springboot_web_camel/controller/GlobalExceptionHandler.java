package com.SnowSoft.springboot_web_camel.controller;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.SnowSoft.springboot_web_camel.util.ErrorResponseDTO;

import jakarta.validation.ConstraintViolationException;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponseDTO> validationException(MethodArgumentNotValidException e){
		
		List<String> errorsList = e.getBindingResult().getFieldErrors().stream()
	            .map(FieldError::getDefaultMessage)
	            .collect(Collectors.toList());
		
		ErrorResponseDTO errorResponse = new ErrorResponseDTO(
				HttpStatus.BAD_REQUEST, "One or more fields are invalid", 1,  errorsList);
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleConstraintViolationException(ConstraintViolationException e) {
        List<String> errorsList = e.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.toList());

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST, "Validation error", 1, errorsList);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
