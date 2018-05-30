/**
 * 
 */
package com.stackroute.maverick.domain;

import java.util.List;

/**
 * @author ajay
 *
 */
public class MatchingData {

	private int gameId;
	List<Users> users;
	
	
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public List<Users> getUsers() {
		return users;
	}
	public void setUsers(List<Users> users) {
		this.users = users;
	}
	public MatchingData(int gameId, List<Users> users) {
		super();
		this.gameId = gameId;
		this.users = users;
	}
	
	
	public MatchingData() {
		
	}
	
	
}
