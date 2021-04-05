package com.py.aso.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.py.aso.dto.ExceptionDTO;
import com.py.aso.exception.ResourceNotFoundException;

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
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionDTO resourceNotValid(final DataIntegrityViolationException ex) {
		LOGGER.warn(ex.getMessage());
		return new ExceptionDTO("No se pudo realizar los cambios por violación a las politicas");
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionDTO exceptionExceptions(final Exception ex) {
		LOGGER.error("Error interno: " + ex);
		return new ExceptionDTO("Problemas internos del servidor");
	}
}
