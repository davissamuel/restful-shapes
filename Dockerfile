FROM openjdk:18
ADD target/restful-shapes-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]