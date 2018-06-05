package com.stackroute.maverick.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.stackroute.maverick.domain.ReportingData;
import com.stackroute.maverick.domain.Users;
import com.stackroute.maverick.repository.ReportDataRepository;
import com.stackroute.maverick.repository.UsersRepository;

@Service
public class Results {
	
	int starttingScore = 0;
	@Autowired
	UsersRepository usersRepo;

	@Bean
	public Users winningUser() {
		return new Users();
	}

	@Bean
	public ReportingData reportingData() {
		return new ReportingData();
	}

	@Autowired
	Users winningUser1;

	@Autowired
	KafkaProducer kafkaProducer;

	@Autowired
	ReportDataRepository reportDataRepository;

	@Autowired
	ReportingData reportingData;

	@Autowired
	ReportDataImpl reportDataImpl;

	int counter = 0;

	@Autowired
	public Results(ReportDataRepository reportDataRepository) {
		super();
		
		this.reportDataRepository = reportDataRepository;
	}

	public Users getResults() {
		counter++;
		Iterable<Users> users = usersRepo.findAll();
		System.out.println("Hi get results");
		if (counter == 1) {
			for (Users user : users) {
				int score = user.getScore();
				System.out.println("Score" + score + "Users" + user.getUserId());
				int userId = user.getUserId();
				Optional<ReportingData> oneUser = reportDataRepository.findByUserId(userId);

				System.out.println("User id after query" + oneUser.isPresent());
				ReportingData saveUser = new ReportingData();
				ReportingData reportData = oneUser.get();
				saveUser.setScore(score);
				
				saveUser.setGameDetails(reportData.getGameDetails());
				saveUser.setReportQuestions(reportData.getReportQuestions());
				saveUser.setUserId(reportData.getUserId());
				kafkaProducer.sendReportingData("reportingtopic.t", saveUser);
				System.out.println("looser" + saveUser.getScore());
				winningUser1.setScore(score);

				if (winningUser1.getScore() <= starttingScore) {
                    starttingScore = user.getScore();
					winningUser1.setScore(user.getScore());
					winningUser1.setGameId(user.getGameId());
					winningUser1.setUserId(user.getUserId());
					Optional<ReportingData> winnerUser = reportDataRepository.findById(winningUser1.getUserId());
					ReportingData saveWinningUser = winnerUser.get();
					ReportingData winner = new ReportingData();
					saveWinningUser.setScore(winningUser1.getScore());
					saveWinningUser.setGameDetails(winner.getGameDetails());
					saveWinningUser.setReportQuestions(winner.getReportQuestions());
					saveWinningUser.setUserId(winner.getUserId());
					System.out.println(saveWinningUser.getScore() + " Winner");
					//reportDataImpl.scoreUpdate(saveWinningUser);
					System.out.println("Data sent");

				}
				
				

			}

			counter = 0;
		}

     return winningUser1;

	}
}
