package com.stackroute.maverick.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.stackroute.maverick.domain.MatchingData;
import com.stackroute.maverick.domain.MultiPlayerGame;
import com.stackroute.maverick.domain.MultiPlayerGameResponseData;
import com.stackroute.maverick.domain.MultiPlayerModel;
import com.stackroute.maverick.domain.MultipleQuestions;
import com.stackroute.maverick.domain.Users;
import com.stackroute.maverick.repository.MultiPlayerModelRepository;
import com.stackroute.maverick.repository.UsersRepository;
import com.stackroute.maverick.service.MultiPlayerAssessmentImpl;
import com.stackroute.maverick.service.MultiPlayerModelService;
import com.stackroute.maverick.service.UserService;

@CrossOrigin(value = "*")
@Controller
@RequestMapping("/maverick")
public class MultiPlayerFFFGame {

	int counter = 0;
	int responses = 0;
	MultiPlayerModel multiPlayerGameQuestion;
	MatchingData matchingData;
	List<MultipleQuestions> quest;
	List<MultipleQuestions> question;
	MultipleQuestions q;

	// @Autowired
	Users users = new Users();

	int i = 0;
	String message;

	@Autowired
	MultiPlayerGameResponseData responseData;

	@Bean
	public MultiPlayerGameResponseData responseData() {
		return new MultiPlayerGameResponseData();
	}

	@Bean
	public MultiPlayerModel multiPlayerModel() {
		return new MultiPlayerModel();
	}

	@Autowired
	MultiPlayerModel multiPlayerModel;

	Set<Users> set = new HashSet<>();
	@Value("${spring.data.rest.base-path}")
	String url;

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

	@Bean
	public MultipleQuestions multipleQuestions() {
		return new MultipleQuestions();
	}

	@MessageMapping("/privateMessage")
	@SendTo("/topicResponse/reply")
	public String storeResponse(@Payload String message) throws Exception {
		responses++;
		Gson data = new Gson();
		MultiPlayerGameResponseData result;

		System.out.println("Private topic" + message);
		int userId = Integer.parseInt((data.fromJson(message, Map.class).get("userId").toString()));
		int endTime = Integer.parseInt((String) (data.fromJson(message, Map.class).get("endTime")));
		int qId = Integer.parseInt(data.fromJson(message, Map.class).get("questionId").toString());
		responseData.setSelectedOption(data.fromJson(message, Map.class).get("selectedOption").toString());
		responseData.setQuestionStamp(data.fromJson(message, Map.class).get("questionStamp").toString());
		responseData.setCorrectAns(data.fromJson(message, Map.class).get("correctAns").toString());

		responseData.setEndTime(endTime);
		responseData.setUserId(userId);
		responseData.setQuestionId(qId);
		System.out.println(responses);
		result = multiPlayerAssessmentImpl.MultiPlayerFastestFingerFirstAssessment(responseData);
		if (result.equals(null)) {

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

	@MessageMapping("/messageOpen")
	@SendTo("/topicQuestion/reply")
	// @Scheduled(fixedRate = 10000)
	public MultipleQuestions sendQuestionToAll(@Payload String message) throws Exception {
		counter++;
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

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Users>> matchingAllUsers(Users users) {
		System.out.println("Method hit");
		Iterable<Users> user = userService.getAllUsers();
		System.out.println();
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

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

	@GetMapping("/getQuestionsFromGameManager")
	public MultiPlayerGame getQuestionsFromGameManager() {
		
		
		
		System.out.println("Method hit");
		MultiPlayerGame d = restTemplate.getForObject(url, MultiPlayerGame.class);
        multiPlayerModelService.create(d);
		System.out.println("Save");

		
		return d;
	}

}
