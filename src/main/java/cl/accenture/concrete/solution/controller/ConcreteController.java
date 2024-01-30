package cl.accenture.concrete.solution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.accenture.concrete.solution.domain.Login;
import cl.accenture.concrete.solution.domain.Registro;
import cl.accenture.concrete.solution.exception.DesafioException;
import cl.accenture.concrete.solution.services.LoginService;
import cl.accenture.concrete.solution.services.ProfileService;
import cl.accenture.concrete.solution.services.RegistroService;

@RestController
public class ConcreteController {

	@Autowired
	RegistroService registroService;

	@Autowired
	LoginService loginService;

	@Autowired
	ProfileService profileService;

	@PostMapping(value = "/app/concrete/profile/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> profile(@PathVariable String id) throws DesafioException {
		try {
			return new ResponseEntity<Object>(profileService.getProfile(id), HttpStatus.OK);
		} catch (DesafioException e) {
			throw e;
		}
	}

	@PostMapping(value = "/app/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> access(@RequestBody Login login) throws DesafioException {
		try {
			return new ResponseEntity<Object>(loginService.access(login), HttpStatus.OK);
		} catch (DesafioException e) {
			throw e;
		}
	}

	@PostMapping(value = "/app/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> registro(@RequestBody Registro registro) throws DesafioException {
		try {
			return new ResponseEntity<Object>(registroService.crear(registro), HttpStatus.OK);
		} catch (DesafioException e) {
			return new ResponseEntity<Object>(e.getMessage(), e.getStatus());
		}
	}

}
