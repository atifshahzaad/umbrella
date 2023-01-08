# syntax=docker/dockerfile:experimental
FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} target/ou-eureka-0.0.1-SNAPSHOT.jar
RUN java -Djarmode=layertools -jar target/ou-eureka-0.0.1-SNAPSHOT.jar extract --destination target/extracted

FROM eclipse-temurin:17-jdk-alpine
#RUN addgroup -S ou && adduser -S ou -G ou
VOLUME /tmp
#USER ou
ARG EXTRACTED=/workspace/app/target/extracted
WORKDIR application
COPY --from=build ${EXTRACTED}/dependencies/ ./
COPY --from=build ${EXTRACTED}/spring-boot-loader/ ./
COPY --from=build ${EXTRACTED}/snapshot-dependencies/ ./
COPY --from=build ${EXTRACTED}/application/ ./
ENTRYPOINT ["java","-noverify","-XX:TieredStopAtLevel=1","-Dspring.main.lazy-initialization=true","org.springframework.boot.loader.JarLauncher"]