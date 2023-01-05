package com.ou.controller.advisor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ou.exceptions.InvalidOperationException;
import com.ou.exceptions.ResourceNotFoundException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ControllerAdvice
public class OuAcountControllerAdvisor extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		RequestBodyInvalidErrorResponse response = new RequestBodyInvalidErrorResponse();
		List<FieldErrorDTO> list = new ArrayList<>();

		List<FieldError> errors = ex.getFieldErrors();
		for (FieldError error : errors) {
			list.add(new FieldErrorDTO(error.getField(), error.getDefaultMessage()));
		}

		response.setTimestamp(LocalDateTime.now());
		response.setStatus(status);
		response.setMessage("Invalid request body");
		response.setErrors(list);

		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidOperationException.class)
	public ResponseEntity<RequestBodyInvalidErrorResponse> handleInvalidOperationException(
			InvalidOperationException ex, WebRequest request) {

		RequestBodyInvalidErrorResponse response = new RequestBodyInvalidErrorResponse();

		response.setTimestamp(LocalDateTime.now());
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setMessage(ex.getMessage());
		response.setErrors(Collections.emptyList());
		
		return new ResponseEntity<RequestBodyInvalidErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<RequestBodyInvalidErrorResponse> handleResourceNotFoundException(
			ResourceNotFoundException ex, WebRequest request) {

		RequestBodyInvalidErrorResponse response = new RequestBodyInvalidErrorResponse();

		response.setTimestamp(LocalDateTime.now());
		response.setStatus(HttpStatus.NOT_FOUND);
		response.setMessage(ex.getMessage());
		response.setErrors(Collections.emptyList());
		
		return new ResponseEntity<RequestBodyInvalidErrorResponse>(response, HttpStatus.NOT_FOUND);
	}
	
	@Data
	public static class RequestBodyInvalidErrorResponse {
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
		private LocalDateTime timestamp;
		private HttpStatusCode status;
		private String message;
		List<FieldErrorDTO> errors;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class FieldErrorDTO {
		String field;
		String message;
	}

}
