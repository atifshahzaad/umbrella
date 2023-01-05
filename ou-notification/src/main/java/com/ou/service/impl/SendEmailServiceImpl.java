package com.ou.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ou.email.Mail;
import com.ou.service.SendEmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SendEmailServiceImpl implements SendEmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public boolean sendMail(Mail mail) {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;

		try {
			helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setTo(mail.getMailTo());
			helper.setSubject(mail.getMailSubject());
			helper.setText(mail.getMailContent(), true);
			mailSender.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return false;

	}

}
