package com.asiantech.onlineauction.service.imple;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.asiantech.onlineauction.service.MailService;
@Service(MailService.NAME)
@Transactional
public class MailServiceImpl implements MailService {
	
    @Autowired
    private MailSender mailSender;
    @Autowired
    private SimpleMailMessage alertMailMessage;
    
    
	@Override
	@Transactional
	public void sendMail(String from, String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
        
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);

	}


}
