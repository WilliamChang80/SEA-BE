FROM openjdk:8-jdk-alpine
COPY target/demo-0.0.1-SNAPSHOT.jar app/demo-docker.jar
ENTRYPOINT ["java","-jar","app/demo-docker.jar"]
