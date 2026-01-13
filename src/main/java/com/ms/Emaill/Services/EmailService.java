package com.ms.Emaill.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ms.Emaill.EmailRepositry.EmailRepository;
import com.ms.Emaill.Entity.EmailModel;
import com.ms.Emaill.modelStatus.StatusEmail;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor 
public class EmailService { 
	private final EmailRepository emailRepository;
	private final JavaMailSender emailSender;
	
	
	
	@Value("${spring.mail.username}")
	private String emailFrom;
	//envios de emails
	@Transactional   
	public EmailModel  sendEmail(EmailModel emailModel) {
		try {
			emailModel.setSendDateEmail(LocalDateTime.now());
			emailModel.setEmailFrom(emailFrom);
		 	// 
			SimpleMailMessage message = new SimpleMailMessage();
	
			message.setFrom(emailFrom);
			message.setTo(emailModel.getEmailTo()); 
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getText());
			
			emailSender.send(message); 
			emailModel.setStatus(StatusEmail.SENT); 
			
		}catch(MailException e) { 
		System.out.print(e.getMessage());
			emailModel.setStatus(StatusEmail.ERROR);
		} 
		finally {
			return emailRepository.save(emailModel);
		}
	}
	 
}
