/**
 * 
 */
package com.stackroute.maverick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.maverick.domain.Users;
import com.stackroute.maverick.repository.UsersRepository;

/**
 * @author ajay
 *
 */
@Service
public class UserServiceImpl implements UserService {

	UsersRepository usersRepo;

	@Autowired
	public UserServiceImpl(UsersRepository usersRepo) {
		super();
		this.usersRepo = usersRepo;
	}

	@Override
	public Users findByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users findByGameId(int gameId) {
		// TODO Auto-generated method stub
		Users users = usersRepo.findByuserId(gameId);

		return users;
	}

	@Override
	public Iterable<Users> getAllUsers() {
		System.out.println("Entered the service");
		Iterable<Users> users = usersRepo.findAll();
		System.out.println();
		return users;
	}

}
