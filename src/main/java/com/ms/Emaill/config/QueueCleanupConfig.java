package com.ms.Emaill.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.rabbitmq.client.Channel;

import jakarta.annotation.PostConstruct;

@Configuration
public class QueueCleanupConfig {
	
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Value("${broker.queues.email:default.email}")
    private String queueName;
    @Value("${spring.rabbitmq.addresses}")
    private String rabbitUri;
     
    //limpa a fila quando a aplicacao sou pra evitar lupes infinitos
 @PostConstruct 
 public void clearQueueOnStartup() {
        System.out.println("🔄 Iniciando limpeza da fila: " + queueName);

// Usando a biblioteca nativa do RabbitMQ
com.rabbitmq.client.ConnectionFactory factory = new com.rabbitmq.client.ConnectionFactory();
try {
    factory.setUri(rabbitUri); 
    
    try (com.rabbitmq.client.Connection connection = factory.newConnection();
         Channel channel = connection.createChannel()) {
        
        // Tenta acessar a fila
    try {
   //  channel.queueDeclarePassive(queueName);
        // Limpa a fila
        channel.queuePurge(queueName);
        System.out.println(" Fila '" + queueName + "' limpa com sucesso!");
    } catch (Exception e) {
        System.out.println(" Fila '" + queueName + "' não existe. Criando...");
        Map<String, Object> args = new HashMap<>();
        args.put("x-max-length", 1000);
        channel.queueDeclare(queueName, true, false, false, args);
        System.out.println(" Fila '" + queueName + "' criada!");
        }
    }
} catch (Exception e) {
    System.err.println(" ERRO ao limpar/criar fila: " + e.getMessage());
    }
}

   //pra genrenciaar os queuePurge deletes etc..
    @Bean
    @Primary
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new org.springframework.amqp.rabbit.core.RabbitAdmin(connectionFactory);
    
    
    }
}
