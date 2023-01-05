package com.ou.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ou.email.EmailBuilder;
import com.ou.email.Mail;
import com.ou.event.UserCreatedEvent;
import com.ou.model.Email;
import com.ou.repository.EmailRepository;
import com.ou.service.EmailService;
import com.ou.service.SendEmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailRepository emailRepository;

	@Autowired
	private SendEmailService sendEmailService;

	@Override
	public void handleUserCreatedEvent(UserCreatedEvent event) {

		ObjectMapper mapper = new ObjectMapper();

		try {
			String data = mapper.writeValueAsString(event);
			Email email = new Email(event, data);
			Email persistedEmail = emailRepository.save(email);

			EmailBuilder emailBuilder = new EmailBuilder("Welcome to Umbrella", event.getEmail(),
					"welcome-password-reset.html");
			emailBuilder.setValue("subject", "Welcome");
			//emailBuilder.setValue("content", event.getPassword());

			Mail mail = emailBuilder.createMail();
			boolean sent = sendEmailService.sendMail(mail);

			if (sent) {
				persistedEmail.setCompleted(true);
				emailRepository.save(persistedEmail);
			}

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

}
