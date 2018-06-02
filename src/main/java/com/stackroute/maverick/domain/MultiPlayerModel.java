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
	
	private List<MultipleQuestions> questions;

	public MultiPlayerModel(int gameId, int gameSessionId, List<MultipleQuestions> questions) {
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

	public List<MultipleQuestions> getQuestions() {
		return questions;
	}

	public void setQuestions(List<MultipleQuestions> questions) {
		this.questions = questions;
	}

	public MultiPlayerModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
