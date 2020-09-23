package br.com.mbs.testmarcelo.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(
	  ConstraintViolationException ex, WebRequest request) {	    
	    StringBuilder messageError = new StringBuilder();
	    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	    	messageError.append(violation.getRootBeanClass().getName() + " " + 
	          violation.getPropertyPath() + ": " + violation.getMessage());
	    	messageError.append("\n");
	    }
	 
	    return  new ResponseEntity<>(messageError.toString(),HttpStatus.METHOD_NOT_ALLOWED);
	    
	    
	}
}
