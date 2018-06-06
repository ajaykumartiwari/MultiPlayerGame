/**
 * 
 */
package com.stackroute.maverick.service;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	UsersRepository usersRepo;

	@Autowired
	Users winningUser;

	@Autowired
	KafkaProducer kafkaProducer;

	@Autowired
	ReportDataRepository reportDataRepository;

	 
	ReportingData reportingData = new ReportingData();

	@Autowired
	ReportDataImpl reportDataImpl;

	@Autowired
	public UserServiceImpl(UsersRepository usersRepo) {
		super();
		this.usersRepo = usersRepo;
	}
	
	
	public Users saveUsers(Users users) {
		// TODO Auto-generated method stub
		users = usersRepo.save(users);

		return users;
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

	// @Override
	// public Users getResults() {
	//
	// Iterable<Users> users = usersRepo.findAll();
	// System.out.println("Hi get results");
	// for (Users user : users) {
	// int score = user.getScore();
	// System.out.println("Score" + score + "Users" + user.getGameId());
	// Optional<ReportingData> oneUser =
	// reportDataRepository.findById(user.getUserId());
	//
	// System.out.println("User id after query" + oneUser.isPresent());
	// ReportingData saveUser = new ReportingData();
	// ReportingData reportData = oneUser.get();
	// saveUser.setScore(reportData.getScore());
	// saveUser.setGameDetails(reportData.getGameDetails());
	// saveUser.setReportQuestions(reportData.getReportQuestions());
	// saveUser.setUserId(reportData.getUserId());
	// reportDataImpl.scoreUpdate(saveUser);
	// winningUser.setScore(0);
	// if (winningUser.getScore() <= score) {
	//
	// winningUser.setGameId(user.getScore());
	// winningUser.setGameId(user.getGameId());
	// winningUser.setUserId(user.getUserId());
	// Optional<ReportingData> winnerUser =
	// reportDataRepository.findById(winningUser.getUserId());
	// ReportingData saveWinningUser = winnerUser.get();
	// ReportingData winner = new ReportingData();
	// saveWinningUser.setScore(winner.getScore());
	// saveWinningUser.setGameDetails(winner.getGameDetails());
	// saveWinningUser.setReportQuestions(winner.getReportQuestions());
	// saveWinningUser.setUserId(winner.getUserId());
	// reportDataImpl.scoreUpdate(saveWinningUser);
	// System.out.println("Data sent");
	//
	// }
	//
	// }
	//
	// return winningUser;
	//
	// }

}
