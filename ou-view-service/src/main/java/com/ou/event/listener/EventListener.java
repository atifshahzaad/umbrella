package com.ou.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ou.event.UserCreatedEvent;
import com.ou.event.UserNameUpdatedEvent;
import com.ou.event.UserRoleUpdatedEvent;
import com.ou.event.UserUpdatedEvent;
import com.ou.model.Project;
import com.ou.service.ProjectService;
import com.ou.service.UserService;

@Service
public class EventListener {

	@Autowired
	private UserService userService;

	@Autowired
	private ProjectService projectService;

	@KafkaListener(topics = "${ou-view-service.topic.userCreated}")
	public void onUserCreated(UserCreatedEvent event) {

		System.out.println(event.toString());

		try {
			userService.create(event);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@KafkaListener(topics = "${ou-view-service.topic.userNameUpdated}")
	public void onUserNameUpdated(UserNameUpdatedEvent event) {

		try {
			userService.updateName(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@KafkaListener(topics = "${ou-view-service.topic.userUpdated}")
	public void onUserUpdated(UserUpdatedEvent event) {

		try {
			userService.updateUser(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@KafkaListener(topics = "${ou-view-service.topic.userRoleUpdated}")
	public void onUserRoleUpdated(UserRoleUpdatedEvent event) {

		try {
			userService.updateRoles(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@KafkaListener(topics = "${ou-view-service.topic.projectCreated}")
	public void onProjectCreated(Project project) {

		System.out.println("Project received ###########");

		try {
			projectService.create(project);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
