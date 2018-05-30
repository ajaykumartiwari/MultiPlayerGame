/**
 * 
 */
package com.stackroute.maverick.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ajay
 *
 */
@Document(collection = "gameResponse")
public class MultiPlayerGameResponseData {

	private int userId;
	private String userName;
	private int questionId;
	private int questionLevel;
	private String questionStamp;
	private String options[];
	private String selectedOption;
	private String correctAns;
	private int totalTime;
	private int startTime;
	private int endTime;
	
	
	
	public MultiPlayerGameResponseData(int userId, String userName, int questionId, int questionLevel,
			String questionStamp, String[] options, String selectedOption, String correctAns, int totalTime,
			int startTime, int endTime) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.questionId = questionId;
		this.questionLevel = questionLevel;
		this.questionStamp = questionStamp;
		this.options = options;
		this.selectedOption = selectedOption;
		this.correctAns = correctAns;
		this.totalTime = totalTime;
		this.startTime = startTime;
		this.endTime = endTime;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public int getQuestionId() {
		return questionId;
	}



	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}



	public int getQuestionLevel() {
		return questionLevel;
	}



	public void setQuestionLevel(int questionLevel) {
		this.questionLevel = questionLevel;
	}



	public String getQuestionStamp() {
		return questionStamp;
	}



	public void setQuestionStamp(String questionStamp) {
		this.questionStamp = questionStamp;
	}



	public String[] getOptions() {
		return options;
	}



	public void setOptions(String[] options) {
		this.options = options;
	}



	public String getSelectedOption() {
		return selectedOption;
	}



	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}



	public String getCorrectAns() {
		return correctAns;
	}



	public void setCorrectAns(String correctAns) {
		this.correctAns = correctAns;
	}



	public int getTotalTime() {
		return totalTime;
	}



	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}



	public int getStartTime() {
		return startTime;
	}



	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}



	public int getEndTime() {
		return endTime;
	}



	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}



	public MultiPlayerGameResponseData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
