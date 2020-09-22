FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/test_marcelo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} test_marcelo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/test_marcelo-0.0.1-SNAPSHOT.jar"]
