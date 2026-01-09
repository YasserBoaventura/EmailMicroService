package com.ms.Emaill.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
	
	@RabbitListener(queues = "${broker.queues.email.name}")
	public void listenEmailQueue (@Payload String string ) {
		System.out.print(string);
		
	}

}
