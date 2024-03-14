FROM openjdk:17-jdk-slim
COPY target/coach-finder-1.0.war app.war
ENTRYPOINT ["java","-jar","/app.war"]