package com.stackroute.maverick.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reportdata")
public class ReportingData {
	
	
	@Id
	private int userId;
	private GameDetails gameDetails;
	private int score;
	private List<ReportQuestions> reportQuestions;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public GameDetails getGameDetails() {
		return gameDetails;
	}

	public void setGameDetails(GameDetails gameDetails) {
		this.gameDetails = gameDetails;
	}

	public List<ReportQuestions> getReportQuestions() {
		return reportQuestions;
	}

	public void setReportQuestions(List<ReportQuestions> reportQuestions) {
		this.reportQuestions = reportQuestions;
	}

}
