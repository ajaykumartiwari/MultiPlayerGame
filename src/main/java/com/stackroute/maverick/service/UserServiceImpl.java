/**
 * 
 */
package com.stackroute.maverick.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.stackroute.maverick.domain.ReportingData;
import com.stackroute.maverick.domain.Users;
import com.stackroute.maverick.repository.ReportDataRepository;
import com.stackroute.maverick.repository.UsersRepository;

/**
 * @author ajay
 *
 */
@Service
public class UserServiceImpl implements UserService {

	UsersRepository usersRepo;

	@Bean
	public Users winningUser() {
		return new Users();
	}

	@Autowired
	Users winningUser;

	@Autowired
	KafkaProducer kafkaProducer;

	ReportDataRepository reportDataRepository;

	@Autowired
	ReportingData reportingData;

	@Autowired
	ReportDataImpl reportDataImpl;

	@Autowired
	public UserServiceImpl(UsersRepository usersRepo) {
		super();
		this.usersRepo = usersRepo;
	}

	@Override
	public Users findByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users findByGameId(int gameId) {
		// TODO Auto-generated method stub
		Users users = usersRepo.findByuserId(gameId);

		return users;
	}

	@Override
	public Iterable<Users> getAllUsers() {
		System.out.println("Entered the service");
		Iterable<Users> users = usersRepo.findAll();
		System.out.println();
		return users;
	}

	@Override
	public Users getResults() {

		Iterable<Users> users = usersRepo.findAll();
		for (Users user : users) {
			int score = user.getScore();
			Optional<ReportingData> oneUser = reportDataRepository.findById(user.getUserId());
			ReportingData saveUser = new ReportingData();
			saveUser.setScore(oneUser.get().getScore());
			saveUser.setGameDetails(oneUser.get().getGameDetails());
			saveUser.setReportQuestions(oneUser.get().getReportQuestions());
			saveUser.setUserId(oneUser.get().getUserId());
			reportDataImpl.scoreUpdate(saveUser);
			winningUser.setScore(0);
			if (winningUser.getScore() <= score) {

				winningUser.setGameId(user.getScore());
				winningUser.setGameId(user.getGameId());
				winningUser.setUserId(user.getUserId());
				Optional<ReportingData> winnerUser = reportDataRepository.findById(winningUser.getUserId());
				ReportingData saveWinningUser = new ReportingData();
				saveWinningUser.setScore(winnerUser.get().getScore());
				saveWinningUser.setGameDetails(oneUser.get().getGameDetails());
				saveWinningUser.setReportQuestions(oneUser.get().getReportQuestions());
				saveWinningUser.setUserId(oneUser.get().getUserId());
				reportDataImpl.scoreUpdate(saveWinningUser);
				System.out.println("Data sent");

			}

		}

		return winningUser;

	}

}
