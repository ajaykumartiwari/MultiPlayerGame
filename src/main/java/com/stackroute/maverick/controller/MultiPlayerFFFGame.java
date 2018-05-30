package com.stackroute.maverick.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.stackroute.maverick.domain.MatchingData;
import com.stackroute.maverick.domain.MultiPlayerGameResponseData;
import com.stackroute.maverick.domain.MultiPlayerModel;
import com.stackroute.maverick.domain.Questions;
import com.stackroute.maverick.domain.Users;
import com.stackroute.maverick.repository.UsersRepository;
import com.stackroute.maverick.service.MultiPlayerAssessmentImpl;
import com.stackroute.maverick.service.MultiPlayerModelService;

@CrossOrigin(value = "*")
@Controller
@RequestMapping("/maverick")
public class MultiPlayerFFFGame {

	int counter = 0;
	MultiPlayerModel multiPlayerGameQuestion;
	MatchingData matchingData;
	List<Questions> quest;
	List<Questions> question;
	Questions q;
	Users users;
	int i = 0;
	String message;

	MultiPlayerGameResponseData responseData;

	Set<Users> set = new HashSet<>();
	String url = "http://172.23.239.205:8080/api/game/games/mp/869917";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	MultiPlayerModelService multiPlayerModelService;

	@Autowired
	private SimpMessageSendingOperations msgTemplate;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	MultiPlayerAssessmentImpl multiPlayerAssessmentImpl;

	@MessageMapping("/privateMessage")
	@SendToUser("/topicResponse/reply")
	public String storeResponse(@Payload String message) throws Exception {

		Gson data = new Gson();
		MultiPlayerGameResponseData result;

		int userId = Integer.parseInt((data.fromJson(message, Map.class).get("userId").toString()));
		int endTime = Integer.parseInt((String) (data.fromJson(message, Map.class).get("endTime")));
		int qId = Integer.parseInt(data.fromJson(message, Map.class).get("questionId").toString());
		responseData.setSelectedOption(data.fromJson(message, Map.class).get("selectedResponse").toString());
		responseData.setQuestionStamp(data.fromJson(message, Map.class).get("questionStamp").toString());
		responseData.setCorrectAns(data.fromJson(message, Map.class).get("correctAns").toString());

		responseData.setEndTime(endTime);
		responseData.setUserId(userId);
		responseData.setQuestionId(qId);

		result = multiPlayerAssessmentImpl.MultiPlayerFastestFingerFirstAssessment(responseData);

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

	@MessageMapping("/messageOpen")
	@SendTo("/topicQuestion/reply")
	// @Scheduled(fixedRate = 10000)
	public Questions sendQuestionToAll(@Payload String message) throws Exception {
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
		q = new Questions();

		for (int i = 0; i < response.size(); i++) {
			if (responseData.getSelectedOption() == question.iterator().next().correctAnswer) {

			} else {

			}
		}
		// return score;
	}

	@RequestMapping(value = "/users/{gameId}", method = RequestMethod.GET)
	public List<Users> matchingUsers(@PathVariable("gameId") int gameId) {

		return null;

	}

	@MessageExceptionHandler
	public String handleException(Throwable exception) {
		msgTemplate.convertAndSend("/errors", exception.getMessage());
		return exception.getMessage();
	}

	@RequestMapping(value = "/multiPlayer", method = RequestMethod.POST)
	public ResponseEntity<MultiPlayerModel> create(@RequestBody MultiPlayerModel multiPlayerModel) {
		MultiPlayerModel multiPlayer = multiPlayerModelService.create(multiPlayerModel);
		return new ResponseEntity<MultiPlayerModel>(multiPlayer, HttpStatus.OK);
	}

	@RequestMapping(value = "/questions/{gameId}", method = RequestMethod.GET)
	public ResponseEntity<MultiPlayerModel> findByGameId(@PathVariable("gameId") int gameId) throws Exception {
		multiPlayerGameQuestion = multiPlayerModelService.findByGameId(gameId);
		Gson data = new Gson();
		String ss = data.toJson(multiPlayerGameQuestion);
		sendQuestion();
		System.out.println(ss);
		return new ResponseEntity<MultiPlayerModel>(multiPlayerGameQuestion, HttpStatus.OK);
	}

	public List<Questions> sendQuestion() {
		Iterable<MultiPlayerModel> d = (Iterable<MultiPlayerModel>) multiPlayerModelService.getAllQuestions();
		quest = d.iterator().next().getQuestions();
		for (int i = 0; i < quest.size(); i++) {
			System.out.println("Data is ====> :" + quest.get(i).questionStamp);
		}
		return quest;
	}

	@GetMapping("/getQuestionsFromGameManager")
	public List<Questions> getQuestionsFromGameManager() {

		Iterable<MultiPlayerModel> d = restTemplate.getForObject(url, Iterable.class);
		quest = d.iterator().next().getQuestions();
		for (int i = 0; i < quest.size(); i++) {
			System.out.println("Data is ====> :" + quest.get(i).questionStamp);
		}
		return null;
	}

}
