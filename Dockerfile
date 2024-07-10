FROM openjdk:17-oracle
WORKDIR /app
COPY ./build/libs/k8s-*.jar app.jar
EXPOSE 8090
CMD ["java", "-jar", "app.jar"]