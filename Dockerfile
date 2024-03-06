FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]

FROM openjdk:17-jdk-alpine

VOLUME /tmp

COPY my-gym-routine-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]