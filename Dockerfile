FROM openjdk:17-oracle
WORKDIR /spring-app
COPY ./build/libs/spring-app*.jar spring-app.jar
CMD ["java", "-jar", "spring-app.jar"]