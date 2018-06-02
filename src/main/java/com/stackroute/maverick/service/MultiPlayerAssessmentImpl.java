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
	private UsersRepository usersRepository;

	@Autowired
	MultiPlayerGameResponseData winningUser;
	int counter = 1;

	public MultiPlayerGameResponseData MultiPlayerFastestFingerFirstAssessment(
			MultiPlayerGameResponseData responseData) {

		int userId = responseData.getUserId();
		System.out.println("User id" + userId);
		String userOption = responseData.getSelectedOption();
		System.out.println("UserOption" + userOption);
		String correctionOption = responseData.getCorrectAns();
		int finishTime = responseData.getEndTime();
		responseData.getQuestionId();
		System.out.println("Correct answers and selected option " + correctionOption + userOption);

		if (userOption.equals(correctionOption)) {
			System.out.println(userOption + correctionOption);
			System.out.println("Inside first operator");

			if (finishTime > endTime) {
				System.out.println("Inside second");
				this.endTime = finishTime;
				System.out.println("EndTime" + endTime);
				winningUser.setUserId(userId);
			}

		} else {
			Users user = usersRepository.findByuserId(responseData.getUserId());
			user.setScore(user.getScore() - 5);
			usersRepository.save(user);
		}
		System.out.println("In assess" + winningUser.getUserId());

		if (counter == 2) {
			counter--;
			return winningUser;

		}
		counter++;
		return null;

	}

}
