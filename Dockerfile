FROM selenium/standalone-chrome:latest

USER root

# Install Java and Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven && \
    apt-get clean

# Set working directory
WORKDIR /app

# Copy pom first for caching
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Give ownership to seluser
RUN chown -R seluser:seluser /app

USER seluser

# -----------------------------
# ENTRYPOINT starts Selenium server always
# -----------------------------
# Start Selenium in background, wait until ready, then run Maven tests
CMD bash -c "/opt/bin/entry_point.sh & echo 'Waiting for Selenium...' && until curl -s http://localhost:4444/status | grep -q '\"ready\": true'; do sleep 2; done && echo 'Selenium ready. Running tests...' && mvn clean test"


