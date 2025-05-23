# Use an official OpenJDK image to run the application
FROM openjdk:17-jdk-slim

# Set environment variable for Java
ENV JAVA_OPTS=""

# Set working directory
WORKDIR /app

# Copy the built JAR into the container
COPY build/libs/*.jar blog-0.0.1-SNAPSHOT.jar

# Expose the application's port
EXPOSE 8080

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar blog-0.0.1-SNAPSHOT.jar"]
