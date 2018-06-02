package com.stackroute.maverick.domain;

import java.util.List;

/**
 * @author ajay
 *
 */

public class MultipleQuestions {

	public int questionId;
	public String questionStamp;
	public String op1;
	public String op2;
	public String op3;
	public String op4;
	public String correctAnswer;
	public int questionTime;
	private int questionScore;
//	private List<Users> users;

	public MultipleQuestions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionStamp() {
		return questionStamp;
	}

	public void setQuestionStamp(String questionStamp) {
		this.questionStamp = questionStamp;
	}

	public String getOp1() {
		return op1;
	}

	public void setOp1(String op1) {
		this.op1 = op1;
	}

	public String getOp2() {
		return op2;
	}

	public void setOp2(String op2) {
		this.op2 = op2;
	}

	public String getOp3() {
		return op3;
	}

	public void setOp3(String op3) {
		this.op3 = op3;
	}

	public String getOp4() {
		return op4;
	}

	public void setOp4(String op4) {
		this.op4 = op4;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public int getQuestionTime() {
		return questionTime;
	}

	public void setQuestionTime(int questionTime) {
		this.questionTime = questionTime;
	}

	public int getQuestionScore() {
		return questionScore;
	}

	public void setQuestionScore(int questionScore) {
		this.questionScore = questionScore;
	}

//	public List<Users> getUsers() {
//		return users;
//	}
//
//	public void setUsers(List<Users> users) {
//		this.users = users;
//	}

	public MultipleQuestions(int questionId, String questionStamp, String op1, String op2, String op3, String op4,
			String correctAnswer, int questionTime, int questionScore) {
		super();
		this.questionId = questionId;
		this.questionStamp = questionStamp;
		this.op1 = op1;
		this.op2 = op2;
		this.op3 = op3;
		this.op4 = op4;
		this.correctAnswer = correctAnswer;
		this.questionTime = questionTime;
		this.questionScore = questionScore;
		
	}

}
