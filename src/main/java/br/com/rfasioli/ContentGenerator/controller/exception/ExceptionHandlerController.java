package br.com.rfasioli.ContentGenerator.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.rfasioli.ContentGenerator.exception.BusinessException;
import br.com.rfasioli.ContentGenerator.exception.MissingParameterException;

@ControllerAdvice 
public class ExceptionHandlerController {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);
	
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	@ExceptionHandler(BusinessException.class)
	public void businessExceptionHandler(BusinessException ex, HttpServletRequest request) {
		logger.info("Ocorreu uma violação na regra de negócio na requisição <?>", request.getRequestURI(), ex);
	}

	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	@ExceptionHandler(MissingParameterException.class)
	public void missingParameterExceptionHandler(BusinessException ex, HttpServletRequest request) {
		logger.info("Parametros inválidos na requisição <?>", request.getRequestURI(), ex);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public void unhandledExceptionHandler(Exception ex, HttpServletRequest request) {
		logger.info("Exception não tratada <?>", request.getRequestURI(), ex);
	}
	
}
