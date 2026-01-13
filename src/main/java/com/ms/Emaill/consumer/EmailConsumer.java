package com.ms.Emaill.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.Emaill.Entity.EmailModel;
import com.ms.Emaill.Services.EmailService;
import com.ms.Emaill.dto.EmailRecordDTO;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor 
public class EmailConsumer {  
	
	private  final EmailService emailService;

	  //ouvindo o broker
	@RabbitListener(queues = "${broker.queues.email.name}",  ackMode = "MANUAL")
	public void listenEmailQueue (@Payload EmailRecordDTO emailrecordDTO) {
      var emalModel = new EmailModel();   
		BeanUtils.copyProperties(emailrecordDTO, emalModel); 
		emailService.sendEmail(emalModel);  
		  
	}    
 
 
} 
 