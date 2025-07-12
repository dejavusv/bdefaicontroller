
FROM openjdk:17-slim

WORKDIR /app

COPY target/*.jar aicontrol-0.0.1-SNAPSHOT.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "aicontrol-0.0.1-SNAPSHOT.jar"]