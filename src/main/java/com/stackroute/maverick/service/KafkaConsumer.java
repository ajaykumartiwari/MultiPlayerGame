/**
 * 
 */
package com.stackroute.maverick.service;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.stackroute.maverick.domain.Users;
import com.stackroute.maverick.repository.UsersRepository;

/**
 * @author ajay
 *
 */
@Service
public class KafkaConsumer {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	Users user;

	@Bean
	public Users user() {
		return new Users();
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

	// For testing CountdownLatch is added. Allows a signal when message is
	// received.
	// private CountDownLatch latch = new CountDownLatch(1);

	// Counter for seeing when message is called

	// /**
	// * method to get count down latch.
	// * For testing if message has been received.
	// * Checks thread completion
	// * @return a latch instance
	// */
	// public CountDownLatch getLatch() {
	// return latch;
	// }
	// Change the topics to partition different or add more methods.
	// @KafkaListener(id = "foo", topics =
	// "#{'${topicOne:annotated1,foo}'.split(',')}")

	/**
	 * Method to listen the topic helloworld. And print the message.
	 * 
	 * @param payload
	 */

//	@KafkaListener(topics = "test.t")
//	public void receive(Map<Integer, Set<Integer>> payload) {
//		LOGGER.info("received payload='{}'", payload.toString());
//
//		for (int i : payload.keySet()) {
//			int gameId = i;
//
//			for (int j : payload.get(gameId)) {
//
//				int userId = j;
//				user.setGameId(gameId);
//				user.setUserId(userId);
//				user.setScore(0);
//				usersRepository.save(user);
//
//			}
//		}
//		System.out.println("This is " + payload);
//	}

}
