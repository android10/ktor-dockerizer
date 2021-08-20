# Pull open jde base image based on Alpine Linux.
FROM openjdk:8-jre-alpine

# Define environment variables
ENV ENV_ARTIFACT_ID ktor-fat-jar.jar
ENV ENV_ARTIFACT_BUILD_DIR ./build/libs
ENV ENV_ARTIFACT_EXEC_DIR /ktor
ENV ENV_APPLICATION_USER ktorapp
ENV ENV_PORT 5000
ENV ENV_PORT_SSL 8443

# Ports intedended to be published
EXPOSE $ENV_PORT
EXPOSE $ENV_PORT_SSL

# Define user to execute application.
RUN adduser -D -g '' $ENV_APPLICATION_USER

# Define working directory
RUN mkdir -p $ENV_ARTIFACT_EXEC_DIR

# Grant permission to working directory.
RUN chown -R $ENV_APPLICATION_USER $ENV_ARTIFACT_EXEC_DIR

# Set execution user.
USER $ENV_APPLICATION_USER

# Copy the current directory contents into the container.
COPY $ENV_ARTIFACT_BUILD_DIR/$ENV_ARTIFACT_ID $ENV_ARTIFACT_EXEC_DIR/$ENV_ARTIFACT_ID
WORKDIR $ENV_ARTIFACT_EXEC_DIR

# Command to execute.
CMD java -server \
    -XX:+UnlockExperimentalVMOptions \
    -XX:+UseCGroupMemoryLimitForHeap \
    -XX:InitialRAMFraction=2 \
    -XX:MinRAMFraction=2 \
    -XX:MaxRAMFraction=2 \
    -XX:+UseG1GC \
    -XX:MaxGCPauseMillis=100 \
    -XX:+UseStringDeduplication \
    -jar $ENV_ARTIFACT_ID \
