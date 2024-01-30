package cl.accenture.concrete.solution.domain;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class DesafioError {

	private HttpStatus errorCode;
	private String message;

	public DesafioError(HttpStatus errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

}
