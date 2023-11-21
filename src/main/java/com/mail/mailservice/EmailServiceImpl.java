package com.mail.mailservice;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements MailService {
	private static final String UTF_8_INCODING = "utf 8 incoding";
	private static final String NEW_USER_ACCOUNT = "new user account";
	@Value("${spring.mail.verify.host}")
	private String host;
	@Value("${spring.mail.username}")
	private String forEmail;
	private final JavaMailSender emailSender;

	@Override
	@Async
	public void sendSampleMailMessage(String name, String to, String token) {
		try {
			SimpleMailMessage message =new SimpleMailMessage();
			message.setSubject(NEW_USER_ACCOUNT);
			message.setFrom(forEmail);
			message.setTo(to);
			message.setText(EmailUtils.getEmailMesage(name, host, token));
			
			emailSender.send(message);
			
		} catch (Exception e) {
		throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	@Async
	public void sendMimeMessageMessageWithAttachments(String name, String to, String token) {
	try {
		MimeMessage mimeMessage =getMimeMessage();
		 MimeMessageHelper messageHelper =new MimeMessageHelper(mimeMessage ,true ,UTF_8_INCODING);
		 messageHelper.setPriority(1);
		 messageHelper.setSubject(NEW_USER_ACCOUNT);
		 messageHelper.setFrom(forEmail);
		 messageHelper.setTo(to);
		 messageHelper.setText(EmailUtils.getEmailMesage(name, host, token));
		 FileSystemResource fileSystemResource =new FileSystemResource(new File(System
				 .getProperty("user.home") +"/Downloads/burpsuite-article-image.jpg"));
		      messageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
		
	} catch (Exception e) {
		throw new RuntimeException(e.getMessage()); 
	}
		
	}

	

	@Override
	public void sendMimeMessageMessageWithEmbeddedImages(String name, String to, String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMimeMessageMessageWithEmbeddedFiles(String name, String to, String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendHtmlEmail(String name, String to, String token) {
		// TODO Auto-generated method stub
		
	}
private MimeMessage getMimeMessage() {
		
		return emailSender.createMimeMessage();
	}

}
