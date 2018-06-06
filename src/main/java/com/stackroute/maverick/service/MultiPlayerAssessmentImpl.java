package com.stackroute.maverick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.maverick.domain.MultiPlayerGameResponseData;
import com.stackroute.maverick.domain.Users;
import com.stackroute.maverick.repository.UsersRepository;

@Service
public class MultiPlayerAssessmentImpl {

	int endTime = 0;

	@Autowired
	UsersRepository usersRepository;

	
	MultiPlayerGameResponseData responseData = new MultiPlayerGameResponseData();
	
	int counter = 1;

	public MultiPlayerGameResponseData MultiPlayerFastestFingerFirstAssessment(
			MultiPlayerGameResponseData data) {
		MultiPlayerGameResponseData defaultUser = new MultiPlayerGameResponseData(); 
		int userId = data.getUserId();
		System.out.println("User id" + userId);
		String userOption = data.getSelectedOption();
		System.out.println("UserOption" + userOption);
		String correctionOption = data.getCorrectAns();
		int finishTime = data.getEndTime();
		data.getQuestionId();
		System.out.println("Correct answers and selected option " + correctionOption + userOption);

		if (userOption.equals(correctionOption)) {
			System.out.println(userOption + correctionOption);
			System.out.println("Inside first operator");

			if (finishTime > endTime) {
				System.out.println("Inside second");
				this.endTime = finishTime;
				System.out.println("EndTime" + endTime);
				responseData.setUserId(userId);
			}

		} else {
			Users user = usersRepository.findByuserId(data.getUserId());
			user.setScore(user.getScore() - 5);
			usersRepository.save(user);
		}
		System.out.println("In assess" + responseData.getUserId());

		if (counter == 2) {
			counter--;
			return responseData;

		}
		counter++;
		return defaultUser;

	}

}
