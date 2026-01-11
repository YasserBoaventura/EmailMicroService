package com.ms.Emaill.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.Emaill.dto.EmailRecordDTO;

import lombok.extern.slf4j.Slf4j;

@Component
public class EmailConsumer { 
	 
	@RabbitListener(queues = "${broker.queues.email.name}",  ackMode = "MANUAL")
	public void listenEmailQueue (@Payload EmailRecordDTO emailrecordDTO) {
	System.out.print("estou escutando: "+emailrecordDTO.emailTo()); 
		  
	}    
 
 
} 
