package cl.accenture.concrete.solution.services;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import cl.accenture.concrete.solution.domain.Registro;
import cl.accenture.concrete.solution.domain.Response;
import cl.accenture.concrete.solution.entity.Users;
import cl.accenture.concrete.solution.exception.DesafioException;
import cl.accenture.concrete.solution.repository.ConcreteRepository;
import cl.accenture.concrete.solution.utils.JWTGenerator;
import cl.accenture.concrete.solution.utils.Util;

@Service
public class RegistroImpl implements RegistroService {

	@Autowired
	ConcreteRepository repo;

	@Autowired
	BCryptPasswordEncoder encoder;

	@Override
	public Object crear(Registro registro) throws DesafioException {
		Response data = new Response();
		Users userValid = repo.findUser(registro.getEmail());

		if (userValid == null) {
			try {
				String uuid = Util.getUIID();
				Users user = new Users();
				user.setId(uuid);
				user.setUsername(registro.getEmail());
				user.setPassword(encoder.encode(registro.getPassword()));
				user.setToken(JWTGenerator.createJWT(registro.getEmail(), registro.getPassword()));

				byte[] json = Util.toJson(registro.getPhones());

				Blob datos = BlobProxy.generateProxy(json);
				user.setDatos(datos);
				Users response = repo.save(user);

				data.setId(response.getId());
				data.setCreated(response.getCreated());
				data.setModified(response.getModified());
				data.setLastLogin(response.getLastLogin());
				data.setToken(response.getToken());
				return data;
			} catch (JsonProcessingException | UnsupportedEncodingException e) {
				throw new DesafioException(HttpStatus.BAD_REQUEST, "El correo ya registrado");
			}
		}
		throw new DesafioException(HttpStatus.CONFLICT, "El correo ya registrado");
	}

}
