package com.stackroute.maverick.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.stackroute.maverick.domain.GameDetails;
import com.stackroute.maverick.domain.MatchingData;
import com.stackroute.maverick.domain.MultiPlayerGame;
import com.stackroute.maverick.domain.MultiPlayerGameResponseData;
import com.stackroute.maverick.domain.MultiPlayerModel;
import com.stackroute.maverick.domain.MultipleQuestions;
import com.stackroute.maverick.domain.ReportingData;
import com.stackroute.maverick.domain.Users;
import com.stackroute.maverick.repository.MultiPlayerModelRepository;
import com.stackroute.maverick.repository.UsersRepository;
import com.stackroute.maverick.service.KafkaProducer;
import com.stackroute.maverick.service.MultiPlayerAssessmentImpl;
import com.stackroute.maverick.service.MultiPlayerModelService;
import com.stackroute.maverick.service.ReportDataImpl;
import com.stackroute.maverick.service.Results;
import com.stackroute.maverick.service.UserService;
import com.stackroute.maverick.service.UserServiceImpl;


/**
 * @author ajay
 *
 */
@CrossOrigin(value = "*")
@Controller
@RequestMapping("/maverick")
public class MultiPlayerFFFGame {
	int resultsCounter = 0;
	int questionCounter = 0;
	int counter = 0;
	int saveCounter = 0;
	int responses = 0;
	MultiPlayerModel multiPlayerGameQuestion;
	MatchingData matchingData;
	List<MultipleQuestions> quest;
	List<MultipleQuestions> question;
	public static MultipleQuestions q;
	public static MultiPlayerGame d;
	public static MultiPlayerModel setQuestions;
	@Autowired
	Users user;

	public static Timer timer = new Timer();

	// @Autowired
	Users users = new Users();

	@Autowired
	Results results;

	int i = 0;
	String message;

	@Bean
	public MultipleQuestions multipleQuestions() {
		return new MultipleQuestions();
	}

	@Bean
	public MultiPlayerGameResponseData responseData() {
		return new MultiPlayerGameResponseData();
	}

	// @Bean
	// public ReportingData reportingData() {
	// return new ReportingData();
	// }

	MultiPlayerModel multiPlayerModel = new MultiPlayerModel();

	@Autowired
	MultiPlayerGameResponseData responseData;

	Set<Users> set = new HashSet<>();
	@Value("${spring.data.rest.base-path}")
	String url;

	@Autowired
	ReportDataImpl reportDataImpl;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	MultiPlayerModelService multiPlayerModelService;

	@Autowired
	UserService userService;

	@Autowired
	private SimpMessageSendingOperations msgTemplate;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	MultiPlayerAssessmentImpl multiPlayerAssessmentImpl;

	@Autowired
	MultipleQuestions multipleQuestions;

	@Autowired
	MultiPlayerModelRepository multiPlayerModelRepository;

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	KafkaProducer kafkaProducer;

	/**
	 * 
	 * Socket connection. Assessment method is called here , and real time scoring
	 * is done. Real-time data is also stored according to user answers in this
	 * method
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	@MessageMapping("/privateMessage")
	@SendTo("/topicResponse/reply")
	public String storeResponse(@Payload String message) throws Exception {

		Gson data = new Gson();
		MultiPlayerGameResponseData result;

		System.out.println("Private topic" + message);

		// Data received as string form , parsed and put into variables
		// Setting into the domain.
		
		int userId = Integer.parseInt((data.fromJson(message, Map.class).get("userId").toString()));
		int endTime = Integer.parseInt((String) (data.fromJson(message, Map.class).get("endTime")));
		int qId = Integer.parseInt(data.fromJson(message, Map.class).get("questionId").toString());
		responseData.setSelectedOption(data.fromJson(message, Map.class).get("selectedOption").toString());
		responseData.setQuestionStamp(data.fromJson(message, Map.class).get("questionStamp").toString());
		responseData.setCorrectAns(data.fromJson(message, Map.class).get("correctAns").toString());
		responseData.setEndTime(endTime);
		responseData.setUserId(userId);
		responseData.setQuestionId(qId);

		ReportingData reportData = reportDataImpl.setQuestionData(responseData);
		GameDetails gameDetails = new GameDetails();

		gameDetails.setGameId(setQuestions.getGameId());
		gameDetails.setGameName(setQuestions.getGameName());
		gameDetails.setGameSessionId(setQuestions.getGameSessionId());
		System.out.println("GameName" + gameDetails.getGameName());
		reportData.setGameDetails(gameDetails);
		reportDataImpl.saveReportingData(reportData);
		saveCounter = 0;

		result = multiPlayerAssessmentImpl.MultiPlayerFastestFingerFirstAssessment(responseData);
		if (result.getUserId() == 0) {

			return null;
		} else {
			Users user = usersRepository.findByuserId(result.getUserId());
			// int score = user.getScore();
			user.setScore(user.getScore() + 5);
			usersRepository.save(user);

			Iterable<Users> allUsers = usersRepository.findAll();

			/* Getting response from user */
			String json = data.toJson(allUsers);

			// users = new Users();
			// users.setUserId(userId);
			return json;

		}
	}

	// @MessageMapping("/messageOpen")
	// @SendTo("/topicQuestion/reply")
	// // @Scheduled(fixedRate = 10000)
	// public MultipleQuestions sendQuestionToAll(@Payload String message) throws
	// Exception {
	// counter++;
	// questionCounter++;
	//
	// if (counter < 2) {
	// return null;
	// }
	//
	// question = sendQuestion();
	// System.out.println("CorrectAns :" +
	// question.iterator().next().correctAnswer);
	//
	// q = question.get(i);
	//
	// if (i < question.size()) {
	// i++;
	// } else {
	// i = 0;
	// }
	// counter--;
	// return q;
	//
	// }
	
	
	
	
	/**
	 * Getting questions from game manager. 
	 * Storing in mongodb.
	 * populate questions on the screen.
	 * 
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	
	
	@MessageMapping("/messageOpen")
	@SendTo("/topicQuestion/reply")
	// @Scheduled(fixedRate = 10000)
	public MultipleQuestions sendQuestionToAll(@Payload String message) throws Exception {
		counter++;
		System.out.println("Method hit");
		d = restTemplate.getForObject(url, MultiPlayerGame.class);
		setQuestions = multiPlayerModelService.create(d);
		System.out.println("Save");

		if (counter < 2) {
			return null;
		}
		question = sendQuestion();
		System.out.println("CorrectAns :" + question.iterator().next().correctAnswer);
		q = question.get(i);
		if (i < question.size()) {
			i++;
		} else {
			i = 0;
		}
		counter = 0;
		return q;
	}

	/**
	 * Getting the response data from the users.
	 * Generate Assessment for the game.
	 *
	 */
	
	public void assessment() {
		List<MultiPlayerGameResponseData> response = new ArrayList<>();
		response.add(responseData);
		q = new MultipleQuestions();

		for (int i = 0; i < response.size(); i++) {
			if (responseData.getSelectedOption() == question.iterator().next().correctAnswer) {

			} else {

			}
		}
		// return score;
	}

	
	/**
	 * Getting all matching users with gameId.
	 * Populating the data on game leader Board. 
	 */
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Users>> matchingAllUsers(Users users) {
		System.out.println("Method hit");
		Iterable<Users> user = userService.getAllUsers();
		System.out.println();
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	/**
	 * Getting users based on gameId.
	 *
	 */
	
	@RequestMapping(value = "/users/{gameId}", method = RequestMethod.GET)
	public ResponseEntity<Users> matchingUsers(@PathVariable("gameId") int gameId) {

		users = userService.findByGameId(gameId);
		return new ResponseEntity<Users>(users, HttpStatus.OK);

	}

	// @MessageExceptionHandler
	// public String handleException(Throwable exception) {
	// msgTemplate.convertAndSend("/errors", exception.getMessage());
	// return exception.getMessage();
	// }

	// @RequestMapping(value = "/multiPlayer", method = RequestMethod.POST)
	// public ResponseEntity<MultiPlayerModel> create(@RequestBody MultiPlayerModel
	// multiPlayerModel) {
	// MultiPlayerModel multiPlayer =
	// multiPlayerModelService.create(multiPlayerModel);
	// return new ResponseEntity<MultiPlayerModel>(multiPlayer, HttpStatus.OK);
	// }
	
	/**
	 * Getting Questions from mongodb based on gameId
	 *
	 */

	@RequestMapping(value = "/questions/{gameId}", method = RequestMethod.GET)
	public ResponseEntity<MultiPlayerModel> findByGameId(@PathVariable("gameId") int gameId) throws Exception {
		multiPlayerGameQuestion = multiPlayerModelService.findByGameId(gameId);
		Gson data = new Gson();
		String ss = data.toJson(multiPlayerGameQuestion);
		sendQuestion();
		System.out.println(ss);
		return new ResponseEntity<MultiPlayerModel>(multiPlayerGameQuestion, HttpStatus.OK);
	}

	public List<MultipleQuestions> sendQuestion() {
		Iterable<MultiPlayerModel> d = (Iterable<MultiPlayerModel>) multiPlayerModelService.getAllQuestions();
		quest = d.iterator().next().getQuestions();
		for (int i = 0; i < quest.size(); i++) {
			System.out.println("Data is ====> :" + quest.get(i).questionStamp);
		}
		return quest;
	}

	// @GetMapping("/getQuestionsFromGameManager")
	// public ResponseEntity<MultiPlayerGame> getQuestionsFromGameManager() {
	//
	// System.out.println("Method hit");
	// d = restTemplate.getForObject(url, MultiPlayerGame.class);
	// setQuestions = multiPlayerModelService.create(d);
	// System.out.println("Save");
	// return new ResponseEntity<MultiPlayerGame>(d, HttpStatus.OK);
	// }

	
	/**
	 * Getting updated score from mongo database.
	 * Generate Final winner report of the game.
	 *
	 */
	@GetMapping("/getResults")
	public ResponseEntity<Users> getResult() {
		resultsCounter++;
		System.out.println("Method has been hit");
		// Users dummyUser = new Users();
		// dummyUser.setScore(0);
		// dummyUser.setGameId(0);
		if (resultsCounter == 1) {
			user = results.getResults();
			resultsCounter = -1;
		}

		System.out.println("After sending");

		System.out.println("Result method hit");
		timer = new Timer();
		timer.schedule(new RemindTask(), 10000);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}

	class RemindTask extends TimerTask {

		/**
		 * Method to send the list, once the given time is up.
		 * 
		 * @author shatayki
		 *
		 */
		public void run() {

			Iterable<Users> allUsers = userServiceImpl.getAllUsers();
			for (Users user : allUsers) {
				user.setScore(0);
				userServiceImpl.saveUsers(user);
				reportDataImpl.deleteData();
				i = 0;

			}
			resultsCounter = 0;
			System.out.println("Scores set to zero");
		}

	}

}
