package cl.accenture.concrete.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.accenture.concrete.solution.entity.Users;

@Repository
public interface ConcreteRepository extends JpaRepository<Users, String> {

	@Query(value = "SELECT h FROM Users h where h.username= ?1")
	public Users findUser(String username);
	
}
