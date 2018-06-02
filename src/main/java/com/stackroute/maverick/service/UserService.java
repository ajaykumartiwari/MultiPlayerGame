/**
 * 
 */
package com.stackroute.maverick.service;

import com.stackroute.maverick.domain.Users;

/**
 * @author ajay
 *
 */
public interface UserService {

	public Users findByGameId(int gameId);

	Users findByUserId(int userId);

	public Iterable<Users> getAllUsers();

}
