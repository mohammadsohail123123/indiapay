# Use the official OpenJDK image based on Debian
FROM openjdk:17-jdk-slim

# Install additional utilities
USER root
RUN apt-get update && \
    apt-get install -y procps && \
    rm -rf /var/lib/apt/lists/*

# Copy the Spring Boot application JAR file
COPY ../../../target/indiapay-0.0.1-SNAPSHOT.jar /server.jar

# Expose the application port
EXPOSE 8000

# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "/server.jar"]

