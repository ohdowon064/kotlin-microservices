FROM openjdk:8-jdk-alpine

ADD target/*.jar microservice.jar

ENTRYPOINT ["java", "-jar", "microservice.jar"]