package uk.co.boots.columbus.cmdb.model.core.rest.support;

import javax.persistence.OptimisticLockException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestOptimisticLockExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { OptimisticLockException.class})
	protected ResponseEntity<RestExceptionResponse> handleLockingException(OptimisticLockException ex, WebRequest request){
		return new ResponseEntity<RestExceptionResponse>(new RestExceptionResponse("Unable to update - data is stale! Please refresh."), HttpStatus.LOCKED);
	}
}
