package cl.accenture.concrete.solution.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import cl.accenture.concrete.solution.domain.Response;
import cl.accenture.concrete.solution.entity.Users;
import cl.accenture.concrete.solution.exception.DesafioException;
import cl.accenture.concrete.solution.repository.ConcreteRepository;

@Service
public class ProfileImpl implements ProfileService {

	@Autowired
	ConcreteRepository repo;

	@Override
	public Object getProfile(String id) throws DesafioException {
		Users users = repo.findOne(id);
		if (users == null) {
			throw new DesafioException(HttpStatus.NO_CONTENT, "No existe usuario");
		}

		Response data = new Response();
		data.setId(users.getId());
		data.setCreated(users.getCreated());
		data.setModified(users.getModified());
		data.setLastLogin(users.getLastLogin());
		data.setToken(users.getToken());
		return data;
	}

}
