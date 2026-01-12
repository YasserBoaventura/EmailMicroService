package com.ms.Emaill.EmailRepositry;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.Emaill.Entity.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, UUID>{

} 
