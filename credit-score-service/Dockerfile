FROM openjdk:8-jdk-alpine

VOLUME /tmp

ADD target/credit-score-service-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch credit-score-service-0.0.1-SNAPSHOT.jar'

EXPOSE 8082

CMD ["java", "-jar", "credit-score-service-0.0.1-SNAPSHOT.jar"]