FROM openjdk:17-oracle
WORKDIR /app
COPY ./build/libs/spring-app*.jar app.jar
EXPOSE 9090
CMD ["java", "-jar", "app.jar"]