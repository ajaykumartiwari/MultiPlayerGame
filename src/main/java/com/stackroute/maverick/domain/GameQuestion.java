/**
 * 
 */
package com.stackroute.maverick.domain;

/**
 * @author ajay
 *
 */
public class GameQuestion {

	private int qId;
	private String questionStamp;
	private String options [];
	private int duration;
	
	public int getqId() {
		return qId;
	}
	public void setqId(int qId) {
		this.qId = qId;
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
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
	public GameQuestion(int qId, String questionStamp, String[] options, int duration) {
		super();
		this.qId = qId;
		this.questionStamp = questionStamp;
		this.options = options;
		this.duration = duration;
	}
	
	public GameQuestion() {}
}
