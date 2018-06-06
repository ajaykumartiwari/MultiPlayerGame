package com.stackroute.maverick.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.maverick.domain.GameDetails;
import com.stackroute.maverick.domain.MultiPlayerGameResponseData;
import com.stackroute.maverick.domain.ReportQuestions;
import com.stackroute.maverick.domain.ReportingData;
import com.stackroute.maverick.repository.MultiPlayerModelRepository;
import com.stackroute.maverick.repository.ReportDataRepository;

@Service
public class ReportDataImpl implements ReportData {

	ReportingData reportingData = new ReportingData();

	public static int counter = 0;
	public static int questionCounter = 0;

	@Autowired
	ReportDataRepository reportDataRepository;
	@Autowired
	MultiPlayerModelRepository multiPlayerModelRepository;

	public List<ReportQuestions> reportQuestionsList = new ArrayList<ReportQuestions>();

	/**
	 * Method which sets the options , selected answers , correct answer , userId,
	 * questionId , question stamp, game name.
	 */
	@Override
	public ReportingData setQuestionData(MultiPlayerGameResponseData responseData) {

		questionCounter++;
		ReportQuestions reportQuestions = new ReportQuestions();
		GameDetails gameDetails = new GameDetails();
		//
		// String[] options = responseData.getOptions();
		// for (int i = 0; i < 4; i++) {
		// if (i == 0)
		// reportQuestions.setOption1(options[i]);
		// else if (i == 1)
		// reportQuestions.setOption2(options[i]);
		// else if (i == 2)
		// reportQuestions.setOption3(options[i]);
		// else
		// reportQuestions.setOption4(options[i]);
		// }
    
		reportQuestions.setCorrectAnswer(responseData.getCorrectAns());
		reportQuestions.setQuestionId(responseData.getQuestionId());
		reportQuestions.setQuestionName(responseData.getQuestionStamp());
		reportQuestions.setSelectedAnswer(responseData.getSelectedOption());
		gameDetails.setGameTypeName(gameDetails.getGameName());
		gameDetails.getGameSessionId();
		reportQuestionsList.add(reportQuestions);
		reportingData.setReportQuestions(reportQuestionsList);
		reportingData.setUserId(responseData.getUserId());
		reportingData.setGameDetails(gameDetails);
		reportDataRepository.save(reportingData);

		return reportingData;
	}

	public void saveReportingData(ReportingData reportingData)

	{
		reportDataRepository.save(reportingData);
	}

	public void scoreUpdate(ReportingData oneUser)

	{

		reportDataRepository.save(oneUser);
	}

	public void deleteData()

	{

		reportDataRepository.deleteAll();
		multiPlayerModelRepository.deleteAll();
	}

}
