package com.stackroute.maverick.domain;

import java.util.Date;
import java.util.List;

public class MultiPlayerGame {

	 private int gameId;
	    private String gameName;
	    private String gameImage;
	    private String createdBy;
	    private Date createdOn;
	    private GameType gameType;
	    private String gameDescription;
	    private String gameRules;
	    //private List<Users> users;
	    private int userId;
	    private int categoryId;
	    private int numberOfQuestions;
	    private GameQuestionLevel questionLevel;
	    private int timePerQuestion;
	    private int scorePerQuestion;
	    
	    private Topic topic;
	    private List<Questions> autoquestions;
		public int getGameId() {
			return gameId;
		}
		public void setGameId(int gameId) {
			this.gameId = gameId;
		}
		public String getGameName() {
			return gameName;
		}
		public void setGameName(String gameName) {
			this.gameName = gameName;
		}
		public String getGameImage() {
			return gameImage;
		}
		public void setGameImage(String gameImage) {
			this.gameImage = gameImage;
		}
		public String getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}
		public Date getCreatedOn() {
			return createdOn;
		}
		public void setCreatedOn(Date createdOn) {
			this.createdOn = createdOn;
		}
		public GameType getGameType() {
			return gameType;
		}
		public void setGameType(GameType gameType) {
			this.gameType = gameType;
		}
		public String getGameDescription() {
			return gameDescription;
		}
		public void setGameDescription(String gameDescription) {
			this.gameDescription = gameDescription;
		}
		public String getGameRules() {
			return gameRules;
		}
		public void setGameRules(String gameRules) {
			this.gameRules = gameRules;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public int getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(int categoryId) {
			this.categoryId = categoryId;
		}
		public int getNumberOfQuestions() {
			return numberOfQuestions;
		}
		public void setNumberOfQuestions(int numberOfQuestions) {
			this.numberOfQuestions = numberOfQuestions;
		}
		public GameQuestionLevel getQuestionLevel() {
			return questionLevel;
		}
		public void setQuestionLevel(GameQuestionLevel questionLevel) {
			this.questionLevel = questionLevel;
		}
		public int getTimePerQuestion() {
			return timePerQuestion;
		}
		public void setTimePerQuestion(int timePerQuestion) {
			this.timePerQuestion = timePerQuestion;
		}
		public int getScorePerQuestion() {
			return scorePerQuestion;
		}
		public void setScorePerQuestion(int scorePerQuestion) {
			this.scorePerQuestion = scorePerQuestion;
		}
		public Topic getTopic() {
			return topic;
		}
		public void setTopic(Topic topic) {
			this.topic = topic;
		}
		public List<Questions> getAutoquestions() {
			return autoquestions;
		}
		public void setAutoquestions(List<Questions> autoquestions) {
			this.autoquestions = autoquestions;
		}
		@Override
		public String toString() {
			return "MultiPlayerGame [gameId=" + gameId + ", gameName=" + gameName + ", gameImage=" + gameImage
					+ ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", gameType=" + gameType
					+ ", gameDescription=" + gameDescription + ", gameRules=" + gameRules + ", userId=" + userId
					+ ", categoryId=" + categoryId + ", numberOfQuestions=" + numberOfQuestions + ", questionLevel="
					+ questionLevel + ", timePerQuestion=" + timePerQuestion + ", scorePerQuestion=" + scorePerQuestion
					+ ", topic=" + topic + ", autoquestions=" + autoquestions + "]";
		}
	    
}
