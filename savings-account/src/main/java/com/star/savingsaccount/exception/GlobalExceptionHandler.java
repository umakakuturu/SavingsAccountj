/**
 * 
 */
package com.star.savingsaccount.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<ErrorResponse> error(TransactionException ex) {
		ErrorResponse er = new ErrorResponse();

		er.setMessage(ex.getMessage());
		er.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErrorResponse> error(RecordNotFoundException ex) {

		ErrorResponse er = new ErrorResponse();
		er.setMessage(ex.getMessage());
		er.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(NotInaRangeException.class)
	public ResponseEntity<ErrorResponse> error(NotInaRangeException ex) {

		ErrorResponse er = new ErrorResponse();

		er.setMessage(ex.getMessage());
		er.setStatus(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE.value());
		return new ResponseEntity<>(er, HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);

	}
	
	@ExceptionHandler(InvalidStatusDateException.class)
	public ResponseEntity<ErrorResponse> error(InvalidStatusDateException ex) {

		ErrorResponse er = new ErrorResponse();

		er.setMessage(ex.getMessage());
		er.setStatus(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE.value());
		return new ResponseEntity<>(er, HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);

	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (ex instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
			List<String> errorList = exception.getBindingResult().getFieldErrors().stream()
					.map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());
			ErrorResponse errorDetails = new ErrorResponse("this is a message from handler", errorList);
			return super.handleExceptionInternal(ex, errorDetails, headers, status, request);
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

}
