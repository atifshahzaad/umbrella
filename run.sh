#!/bin/bash

APPLICATIONS_DOCKER=(keycloack kafka)

# loop through the list of applications
for app in "${APPLICATIONS_DOCKER[@]}"
do
  cd /path/to/$app

  # start the application using the Spring Boot Gradle wrapper
  docker-compose up -d

  echo "Started $app with PID $!"
done

APPLICATIONS=(ou-eureka ou-account ou-hrms ou-notification)

# loop through the list of applications
for app in "${APPLICATIONS[@]}"
do
  # navigate to the application directory
  cd /path/to/$app

  # start the application using the Spring Boot Gradle wrapper
  ./mvnw spring-boot:run

  # print the application name and PID
  echo "Started $app with PID $!"
done

