FROM openjdk:17
COPY target/indiapay-0.0.1-SNAPSHOT.jar server.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "/server.jar"]