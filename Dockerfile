# Use a base image with OpenJDK installed
FROM adoptopenjdk/openjdk11:alpine-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file built by Maven into the container
COPY target/fate-service-0.0.1-SNAPSHOT.jar /app/fate-service-0.0.1-SNAPSHOT.jar

# Expose the port your application runs on
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "fate-service-0.0.1-SNAPSHOT.jar"]
