FROM maven:3.9.4-eclipse-temurin-17-alpine as build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:17-jdk-alpine
COPY --from=buid /app/target/student-management-v1.jar app.jar
ENTRYPOINT [ "java", "-jar" , "app.jar" ]