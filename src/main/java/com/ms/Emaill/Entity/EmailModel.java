package com.ms.Emaill.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.ms.Emaill.modelStatus.StatusEmail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EmailModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID emailId;
	private UUID userId;  
	private String emailFrom;
	private String emailTo; 
	private String subject; 
	@Column(columnDefinition = "TEXT")
	private String text;
	private LocalDateTime sendDateEmail;

	@Enumerated(EnumType.STRING)
    private StatusEmail status;  


}
