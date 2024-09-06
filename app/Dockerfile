## Build Stage
#FROM jelastic/maven:3.9.5-openjdk-21 AS build
#COPY . /app
#WORKDIR /app
#RUN mvn clean package -DskipTests
#
## Runtime Stage
#FROM openjdk:21-jdk
#COPY --from=build /app/target/app-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "--enable-preview", "-jar", "app.jar"]


FROM eclipse-temurin:21-jdk as build
COPY . /app
WORKDIR /app
RUN ./mvnw package -DskipTests
RUN mv -f target/*.jar app.jar

FROM eclipse-temurin:21-jre
ARG PORT=8080
ENV PORT=8080
COPY --from=build /app/app.jar .
RUN useradd runtime
USER runtime
ENTRYPOINT [ "java", "-Dserver.port=8080", "-jar", "app.jar" ]