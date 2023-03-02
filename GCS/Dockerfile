FROM openjdk:17
EXPOSE 8082
ARG JAR_FILE=target/GCS-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]