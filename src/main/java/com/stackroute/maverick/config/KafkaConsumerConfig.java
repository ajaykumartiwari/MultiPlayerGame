///**
// * 
// */
//package com.stackroute.maverick.config;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import com.stackroute.maverick.domain.Users;
//import com.stackroute.maverick.service.KafkaConsumer;
//
///**
// * @author ajay
// *
// */
//@Configuration
//@EnableKafka
//public class KafkaConsumerConfig {
//
//@Value("${kafka.bootstrap-servers}")
//private String bootstrapServers;
//
///**Used to configure the instance of the consumer.
//* @return
//*/
//@Bean
//public Map<String, Object> consumerConfigs() {
//Map<String, Object> props = new HashMap<>();
//// list of host:port pairs used for establishing the initial connections to
//
//// Kafka cluster
//props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
//StringDeserializer.class);
//// allows a pool of processes to divide the work of consuming and processing
//// records
//props.put(ConsumerConfig.GROUP_ID_CONFIG, "helloworld");
//// automatically reset the offset to the earliest offset
//props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//// deserializing the value
//props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
//JsonDeserializer.class);
//// adding all classes to deserialization
//props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//
//return props;
//}
//
//// Working Version
//
///** ConsumerFactory used to create an instance of a consumer
//*
//* @return
//*/
//@Bean
//public ConsumerFactory<String, Object> consumerFactory() {
//return new DefaultKafkaConsumerFactory<>(consumerConfigs());
//}
//
///**The kafkaListenerContainerFactory is responsible to create the listener
/// container for a particular end point.
//*
//* @return Kafka containers created by this condition.
//*/
//@Bean
//public
//KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,
//Object>> kafkaListenerContainerFactory() {
//ConcurrentKafkaListenerContainerFactory<String, Object> factory = new
//ConcurrentKafkaListenerContainerFactory<>();
//factory.setConsumerFactory(consumerFactory());
//
//return factory;
//}
//
//@Bean
//public ConsumerFactory<String, Map<Integer,Set<Integer>>> consumerFactory1()
//{
//return new DefaultKafkaConsumerFactory<>(consumerConfigs());
//}
//
///**The kafkaListenerContainerFactory is responsible to create the listener
/// container for a particular end point.
//*
//* @return Kafka containers created by this condition.
//*/
//@Bean
//public
//KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,
//Map<Integer,Set<Integer>>>> kafkaListenerContainerFactory1() {
//ConcurrentKafkaListenerContainerFactory<String, Map<Integer,Set<Integer>>>
//factory = new ConcurrentKafkaListenerContainerFactory<>();
//factory.setConsumerFactory(consumerFactory1());
//
//return factory;
//}
//
//
//
//@Bean
//public KafkaConsumer receiver() {
//return new KafkaConsumer();
//
//
//}
//
//@Bean
//public Users user() {
//	return new Users();
//}
//}