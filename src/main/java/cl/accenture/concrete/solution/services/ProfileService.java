package cl.accenture.concrete.solution.services;

import cl.accenture.concrete.solution.exception.DesafioException;

/**
 * 
 * @author s2526158
 *
 */
public interface ProfileService {

	public Object getProfile(String id) throws DesafioException;
}
