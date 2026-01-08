FROM openjdk:24-jdk-alpine

COPY target/Sangam-backend.jar Sangam-backend.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","Sangam-backend.jar"]