# Stage 1: Build the JAR
FROM maven:3.9-eclipse-temurin-21 as builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


# Stage 2: Run the app
FROM openjdk:21
WORKDIR /src
COPY --from=builder /app/center/target/center-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

