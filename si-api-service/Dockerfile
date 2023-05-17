FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/si-api-1.0-SNAPSHOT.jar si-api-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/si-api-1.0-SNAPSHOT.jar"]