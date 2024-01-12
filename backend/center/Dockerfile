# the base image
FROM openjdk:21

# the working directory
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/center-0.0.1-SNAPSHOT.jar .

# Expose the port that your application will run on
EXPOSE 8080

#Command to run the application
CMD ["java", "-jar", "center-0.0.1-SNAPSHOT.jar"]

