/**
 * 
 */
package com.stackroute.maverick.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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

	

	int gameId;

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
	 * Method to listen the topic test.t of game card. And save the data in users table.
	 * 
	 * @param payload
	 */

	@KafkaListener(topics = "test.t")
	public void receive(Map<Integer, Set<Integer>> payload) {
		LOGGER.info("received payload='{}'", payload);
		System.out.println("Sixe" + payload.keySet());

		Set set = payload.entrySet();
		Iterator itr = set.iterator();
		while (itr.hasNext())

		{
			Map.Entry mentry = (Map.Entry) itr.next();
			List<Integer> Id = (List<Integer>) mentry.getValue();

			System.out.println("Users" + payload.values());

			
			List<Integer> values = (List<Integer>) payload.values().iterator().next();
			for (Integer i5 : Id) {
				int gameId = payload.keySet().iterator().next();
				for (Integer i1 : values) {
					int userId = i1;
					user.setGameId(gameId);
					user.setUserId(userId);
					user.setScore(0);
					usersRepository.save(user);

				}
			}
		}

		System.out.println("This is " + payload);
	}

}
