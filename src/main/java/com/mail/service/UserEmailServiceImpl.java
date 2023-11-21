package com.mail.service;

import org.springframework.stereotype.Service;

import com.mail.domain.Confirmation;
import com.mail.domain.User;
import com.mail.mailservice.MailService;
import com.mail.repository.ConfirmationRepository;
import com.mail.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class UserEmailServiceImpl implements UserEmailService {
     private final UserRepository userRepository;
     private final ConfirmationRepository confirmationRepository;
    private final MailService emailService ;
	@Override
	public User saveUser(User user) {
		if(userRepository.existsByEmail(user.getEmail()))throw new RuntimeException("email is alredy exist");
		  user.setEnabled(false);
		   userRepository.save(user);
		   Confirmation confirmation = new Confirmation(user);
		   confirmationRepository.save(confirmation);
		   emailService.sendSampleMailMessage(user.getName(), user.getEmail(), confirmation.getToken());
		 //  emailService.sendMimeMessageMessageWithAttachments(user.getName(), user.getEmail(), confirmation.getToken());
		  return user;
	}

	@Override
	public Boolean veryfyToken(String token) {
	   Confirmation confirmation =confirmationRepository.findByToken(token);
	   User user =userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
	   user.setEnabled(true);
	   userRepository.save(user);
	   return Boolean.TRUE;
		 
	}
         
	
}
