package com.ms.Emaill.EmailController;

import java.net.http.HttpResponse.ResponseInfo;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.Emaill.EmailRepositry.EmailRepository;
import com.ms.Emaill.Entity.EmailModel;

import java.util.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class EmailController {

	final EmailRepository emailRepository;
	@GetMapping("/findAll")
	public ResponseEntity<List<EmailModel>> findAll(){
	return ResponseEntity.ok(emailRepository.findAll()); 	
	}

    private final JavaMailSender mailSender;
    
    @GetMapping("/test")
    public String testSSL() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("yasserboaventura78@gmail.com");
            message.setTo("yasserboaventura78@gmail.com");
            message.setSubject("✅ SSL TEST - " + System.currentTimeMillis());
            message.setText("Testando conexão SSL com Gmail...");
            
            mailSender.send(message);
            return "✅ Email enviado! SSL funcionando!";
            
        } catch (Exception e) {
            return "❌ Erro: " + e.getMessage();
        }
    }

}
