FROM openjdk:21
WORKDIR /src

COPY target/center-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
