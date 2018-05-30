/**
 * 
 */
package com.stackroute.maverick.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ajay
 *
 */
@Document(collection = "users")
public class Users {
    private int gameId;
    @Id
	private int userId;
	private int score;
	
	

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Users(int userId, int score) {
		super();
		this.userId = userId;
		this.score = score;
	}

	public Users() {

	}

}
