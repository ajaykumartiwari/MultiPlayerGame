//package com.stackroute.maverick.service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaProducer {
//	private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
//
//	@Autowired
//	private KafkaTemplate<String, Object> kafkaTemplate;
//
//	@Value("${topic.jsa-kafka-topic}")
//	String kafkaTopic;
//
//	public void send(GameEvent event) {
//
//		log.info("sending data='{}'", event);
//		kafkaTemplate.send(kafkaTopic, event);
//	}
//
//}
