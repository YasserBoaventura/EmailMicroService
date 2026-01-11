package com.ms.Emaill.config;



import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.pattern.MessageConverter;



@Configuration
public class RabittMConfig {

 @Value("${broker.queues.email.name}")
 private String queue;
 
 @Bean  
   public Queue queue() {
	 return new Queue(queue, true); 
 }
 
 @Bean 
 public Jackson2JsonMessageConverter messageConverter() {
	 ObjectMapper mapper = new ObjectMapper();
  return new  Jackson2JsonMessageConverter(mapper); 
 }
 }