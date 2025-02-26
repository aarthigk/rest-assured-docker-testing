# Use the official OpenJDK 8 image as the base image
FROM openjdk:8-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Install Maven
RUN apk add --no-cache maven

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the project and run tests
RUN mvn clean install

# Command to run the tests (optional)
CMD ["mvn", "test"]