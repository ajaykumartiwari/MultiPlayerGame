package com.stackroute.maverick.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.stackroute.maverick.domain.ReportingData;
import com.stackroute.maverick.domain.Users;

@Service
public class KafkaProducer {
	private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<ReportingData, Object> kafkaTemplateReportingData;

	public void send(String topic, Users payload) {

		log.info("sending data='{}'", payload);
		kafkaTemplate.send(topic, payload);
	}
	
	public void sendReportingData(String topic, ReportingData payload) {

		log.info("sending data='{}'", payload);
		kafkaTemplateReportingData.send(topic, payload);
	}

}
