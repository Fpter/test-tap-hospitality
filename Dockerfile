# Use a base image with Maven and JDK pre-installed
FROM maven:3.8.3-openjdk-11 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the application JAR
RUN mvn clean package -DskipTests

# Use a smaller base image for the runtime environment
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file built by Maven into the container
COPY target/fate-service-0.0.1-SNAPSHOT.jar /app/fate-service-0.0.1-SNAPSHOT.jar

# Expose the port your application runs on
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "fate-service-0.0.1-SNAPSHOT.jar"]
