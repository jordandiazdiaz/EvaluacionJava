package cl.accenture.concrete.solution.services;

import cl.accenture.concrete.solution.domain.Registro;
import cl.accenture.concrete.solution.exception.DesafioException;

/**
 * 
 * @author s2526158
 *
 */
public interface RegistroService {

	public Object crear(Registro registro) throws DesafioException;

}
