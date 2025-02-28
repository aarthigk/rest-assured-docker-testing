# Use the official OpenJDK 8 image as the base image
FROM openjdk:8-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Install Maven
RUN apk add --no-cache maven

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Copy the TestSuite.xml file explicitly
COPY src/test/java/suite/TestSuite.xml /app/src/test/java/suite/TestSuite.xml

# Build the project and run tests
#RUN mvn clean install
RUN mvn clean install -DskipTests
ENV REPORT_DIR=/app/test-output


# Command to run the tests (optional)
#CMD ["mvn", "test"]
# Command to run tests and ensure reports are saved
CMD ["mvn", "test", "-Dsurefire.reportDirectory=${REPORT_DIR}"]