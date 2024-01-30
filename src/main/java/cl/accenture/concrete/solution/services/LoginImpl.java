package cl.accenture.concrete.solution.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cl.accenture.concrete.solution.domain.Login;
import cl.accenture.concrete.solution.domain.Response;
import cl.accenture.concrete.solution.entity.Users;
import cl.accenture.concrete.solution.exception.DesafioException;
import cl.accenture.concrete.solution.repository.ConcreteRepository;

@Service
public class LoginImpl implements LoginService {

	@Autowired
	ConcreteRepository repo;

	@Autowired
	BCryptPasswordEncoder encoder;

	@Override
	public Object access(Login login) throws DesafioException {

		Users resp = repo.findUser(login.getUsername());

		if (resp == null || !encoder.matches(login.getPassword(), resp.getPassword())) {
			throw new DesafioException(HttpStatus.UNAUTHORIZED, "Usuario o Contraseña Invalido");
		}

		Users modified = repo.save(resp);
		
		Response data = new Response();
		data.setId(modified.getId());
		data.setCreated(modified.getCreated());
		data.setModified(modified.getModified());
		data.setLastLogin(modified.getLastLogin());
		data.setToken(modified.getToken());
		return data;
	}

}
