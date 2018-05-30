package com.stackroute.maverick.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ajay
 *
 */

@Document(collection = "multiPlayer")
public class MultiPlayerModel {

	@Id
	private int gameId;
	private int gameSessionId;
	public int noOfQuestions;
	
	private List<Questions> questions;

	public MultiPlayerModel(int gameId, int gameSessionId, List<Questions> questions) {
		super();
		this.gameId = gameId;
		this.gameSessionId = gameSessionId;
		this.questions = questions;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getGameSessionId() {
		return gameSessionId;
	}

	public void setGameSessionId(int gameSessionId) {
		this.gameSessionId = gameSessionId;
	}

	public List<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}

	public MultiPlayerModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
