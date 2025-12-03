FROM openjdk:21
MAINTAINER alikhan
COPY /build/libs/Exam-0.0.1-SNAPSHOT.jar backend-java.jar
ENTRYPOINT ["java", "-jar", "backend-java.jar"]
