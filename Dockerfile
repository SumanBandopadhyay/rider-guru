# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim
LABEL authors="suman"

# Set the working directory in the container
WORKDIR /app

# Copy the packaged Spring Boot jar into the container
COPY target/rider-guru-*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
