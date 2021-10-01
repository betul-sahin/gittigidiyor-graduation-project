FROM openjdk:8-jdk-alpine

VOLUME /tmp

ADD target/discovery-service-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch discovery-service-0.0.1-SNAPSHOT.jar'

EXPOSE 9090

CMD ["java", "-jar", "discovery-service-0.0.1-SNAPSHOT.jar"]