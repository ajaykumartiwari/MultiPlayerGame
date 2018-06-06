package com.stackroute.maverick.domain;

import org.springframework.data.annotation.Id;

public class ReportQuestions {

	@Id
	private int questionId;
	private String questionName;
	private String correctAnswer;
	private String selectedAnswer;
//	private String option1;
//	private String option2;
//	private String option3;
//	private String option4;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

}
