FROM gradle:7.2-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:11-jre-slim

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/wishlist-service-0.0.1.jar /app/wishlist-service.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/wishlist-service.jar"]