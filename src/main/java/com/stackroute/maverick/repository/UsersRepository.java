package com.stackroute.maverick.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.stackroute.maverick.domain.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {
	
	
	public Users findByuserId(int userId);
	public Users findByGameId(int gameId);
	

}
