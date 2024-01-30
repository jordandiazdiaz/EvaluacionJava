package cl.accenture.concrete.solution.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cl.accenture.concrete.solution.domain.DesafioError;

@ControllerAdvice
public class DesafioControllerAdvice {

	@ExceptionHandler(DesafioException.class)
	public DesafioError handleMyException(DesafioException mex) {
		return new DesafioError(mex.getStatus(), mex.getMessage());
	}
}
