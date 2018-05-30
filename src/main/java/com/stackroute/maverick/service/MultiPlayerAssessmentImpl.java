package com.stackroute.maverick.service;

import org.springframework.stereotype.Service;

import com.stackroute.maverick.domain.MultiPlayerGameResponseData;

@Service
public class MultiPlayerAssessmentImpl {

	int endTime = 0;
	String correctionOption;
	MultiPlayerGameResponseData winningUser;

	public MultiPlayerGameResponseData MultiPlayerFastestFingerFirstAssessment(
			MultiPlayerGameResponseData responseData) {
		int counter = 2;

		int userId = responseData.getUserId();
		String userOption = responseData.getSelectedOption();
		correctionOption = responseData.getCorrectAns();
		int finishTime = responseData.getEndTime();
		responseData.getQuestionId();

		if (userOption.equals(correctionOption)) {
			if (finishTime > endTime) {
				this.endTime = finishTime;
				winningUser.setUserId(userId);

			}
		}

		if (counter == 2) {
			return winningUser;
		}
		return null;

	}

}
