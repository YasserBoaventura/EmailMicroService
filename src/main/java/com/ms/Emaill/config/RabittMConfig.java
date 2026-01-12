package com.ms.Emaill.config;



import java.util.Properties;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

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

 @Bean
 public JavaMailSender javaMailSender() {
     JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
     
     mailSender.setHost("smtp.gmail.com");
     mailSender.setPort(465);
     mailSender.setUsername("yasserboaventura78@gmail.com");
     mailSender.setPassword("ljuh vqvc rcbx ptre");
     
     Properties props = mailSender.getJavaMailProperties();
     props.put("mail.transport.protocol", "smtp");
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.ssl.enable", "true");
     props.put("mail.smtp.socketFactory.port", "465");
     props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
     props.put("mail.smtp.socketFactory.fallback", "false");
     
     // Desabilitar validação SSL (APENAS DESENVOLVIMENTO!)
     props.put("mail.smtp.ssl.trust", "*");
     props.put("mail.smtp.ssl.checkserveridentity", "false");
     
     // Timeouts
     props.put("mail.smtp.connectiontimeout", "30000");
     props.put("mail.smtp.timeout", "30000");
     props.put("mail.smtp.writetimeout", "30000");
     
     // Debug
     props.put("mail.debug", "true");
     
     return mailSender;
 }

 }