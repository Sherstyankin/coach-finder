FROM openjdk:17-jdk-slim
COPY target/coach-finder-1.0-SNAPSHOT.war app.war
ENTRYPOINT ["java","-jar","/app.war"]