package com.mail.mailservice;

public interface MailService {
    void sendSampleMailMessage(String name,String to,String token);
    void sendMimeMessageMessageWithAttachments(String name,String to,String token);
    void sendMimeMessageMessageWithEmbeddedImages(String name,String to,String token);
    void sendMimeMessageMessageWithEmbeddedFiles(String name,String to,String token);
    void sendHtmlEmail(String name,String to,String token);
    		
}
