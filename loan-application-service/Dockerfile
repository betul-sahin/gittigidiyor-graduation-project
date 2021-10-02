FROM openjdk:8-jdk-alpine

VOLUME /tmp

ADD target/loan-application-service-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch loan-application-service-0.0.1-SNAPSHOT.jar'

EXPOSE 8080

CMD ["java", "-jar", "loan-application-service-0.0.1-SNAPSHOT.jar"]