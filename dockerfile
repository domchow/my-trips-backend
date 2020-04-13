FROM alpine:edge
MAINTAINER domchowit@gmail.com
RUN apk --no-cache add openjdk11
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
