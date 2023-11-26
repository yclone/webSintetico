FROM openjdk:11

# Instale o Maven
RUN apt-get update && apt-get install -y maven

WORKDIR /src
COPY . /src

RUN mvn clean install -DskipTests