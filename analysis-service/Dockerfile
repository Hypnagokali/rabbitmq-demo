FROM amazoncorretto:21-alpine
CMD mkdir /application
WORKDIR /application
COPY ./build/libs/analysis-service-0.0.1-SNAPSHOT.jar /application/app.jar
COPY ./application.properties /application/application.properties
ENTRYPOINT ["java", "-jar", "app.jar"]