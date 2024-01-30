package cl.accenture.concrete.solution.services;

import cl.accenture.concrete.solution.domain.Login;
import cl.accenture.concrete.solution.exception.DesafioException;

/**
 * 
 * @author s2526158
 *
 */
public interface LoginService {

	public Object access(Login login) throws DesafioException;

}
