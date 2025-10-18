# Use Maven with Java 17 (includes JDK + Maven)
FROM maven:3.9.9-eclipse-temurin-17

# Set working directory inside container
WORKDIR /app

# Copy Maven descriptor and source
COPY pom.xml .
COPY src ./src

# Download dependencies and build project (skip tests to save time)
RUN mvn clean package -DskipTests

# Run tests when the container starts
CMD ["mvn", "test"]
