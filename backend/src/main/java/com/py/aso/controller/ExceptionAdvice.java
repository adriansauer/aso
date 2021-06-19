package com.py.aso.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.py.aso.dto.ExceptionDTO;
import com.py.aso.exception.InvalidPasswordException;
import com.py.aso.exception.NotAvailableException;
import com.py.aso.exception.ResourceExistsException;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.exception.FilesMaximumException;
import com.py.aso.exception.InvalidArgumentException;

@ControllerAdvice
public class ExceptionAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

	@ResponseBody
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionDTO resourceNotFoundExceptions(final ResourceNotFoundException ex) {
		LOGGER.warn(ex.getMessage());
		return new ExceptionDTO(ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionDTO resourceNotValid(final MethodArgumentNotValidException ex) {
		LOGGER.warn(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		return new ExceptionDTO(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(InvalidPasswordException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionDTO passwordNotValid(final InvalidPasswordException ex) {
		LOGGER.warn(ex.getMessage());
		return new ExceptionDTO(ex.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(ResourceExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionDTO resourceExists(final ResourceExistsException ex) {
		LOGGER.warn(ex.getMessage());
		return new ExceptionDTO(ex.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(NotAvailableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionDTO notAvailable(final NotAvailableException ex) {
		LOGGER.warn(ex.getMessage());
		return new ExceptionDTO(ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(InvalidArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionDTO invalidArgument(final InvalidArgumentException ex) {
		LOGGER.warn(ex.getMessage());
		return new ExceptionDTO(ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionDTO resourceNotValid(final DataIntegrityViolationException ex) {
		LOGGER.warn(ex.getMessage());
		return new ExceptionDTO("No se pudo realizar los cambios por violación a las politicas");
	}

	@ResponseBody
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ExceptionDTO exceptionAccessDeniedException(final Exception ex) {
		LOGGER.warn("	Acceso denegado a un usuario");
		return new ExceptionDTO("Acceso denegado");
	}

	@ResponseBody
	@ExceptionHandler(FilesMaximumException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ExceptionDTO filesMaximun(final FilesMaximumException ex) {
		LOGGER.warn(ex.getMessage());
		return new ExceptionDTO(ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionDTO exceptionExceptions(final Exception ex) {
		LOGGER.error("Error interno: " + ex);
		return new ExceptionDTO("Problemas internos del servidor");
	}

}
