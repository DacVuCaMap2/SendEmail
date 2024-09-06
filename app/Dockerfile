FROM maven:3.9.5-openjdk-21 AS build
COPY . /app/
WORKDIR /app
RUN chmod +x ./mvnw && ./mvnw -DoutputFile=target/mvn-dependency-list.log -B -DskipTests clean dependency:list install

FROM openjdk:21-jdk
COPY --from=build /app/target/app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "--enable-preview", "-jar", "app.jar"]