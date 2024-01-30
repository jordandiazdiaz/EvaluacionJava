/**
 * 
 */
package cl.accenture.concrete.solution.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.accenture.concrete.solution.entity.Users;
import cl.accenture.concrete.solution.entity.UsersDetail;
import cl.accenture.concrete.solution.repository.ConcreteRepository;

/**
 * @author s2526158
 *
 */
@Service
public class UserService implements UserDetailsService {

	@Autowired
	ConcreteRepository repo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {

		Users users = repo.findUser(user);
		
		if (users != null) {
			return new UsersDetail(users);
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado : " + user);
		}
	}

}
