FROM openjdk:11-jdk-slim

COPY target/scala-2.12/counter.jar ./
EXPOSE 8080

CMD java -jar counter.jar