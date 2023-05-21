# Use the official OpenJDK image as the base image
FROM openjdk:17-jdk-buster

# Set the working directory
WORKDIR / .

# Copy the src directory and pom.xml file
COPY /src ./src
COPY /pom.xml .

# Install necessary tools for running the web app
RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://downloads.apache.org/maven/maven-3/3.9.1/binaries/apache-maven-3.9.1-bin.zip && \
    unzip apache-maven-3.9.1-bin.zip && \
    mv apache-maven-3.9.1 /opt/maven && \
    rm apache-maven-3.9.1-bin.zip && \
    ln -s /opt/maven/bin/mvn /usr/local/bin/mvn

# Set environment variables
ENV MAVEN_HOME=/opt/maven
ENV PATH="${MAVEN_HOME}/bin:${PATH}"

# Run Maven to build the project
RUN mvn clean install -DskipTests

# Download project dependencies
RUN mvn dependency:copy-dependencies -DskipTests

# Set the entry point for the container
ENTRYPOINT ["java", "-cp", "target/classes:target/dependency/*", "ch.zhaw.deeplearningjava.pinheluiln2.PinheluiLn2Application"]