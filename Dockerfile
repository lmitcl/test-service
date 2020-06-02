FROM openjdk:11-jdk-slim

COPY target/temmate-service-1.0.jar /opt/temmate-service/

# some tools
RUN apt-get update && apt-get install -y vim tree mc lnav

# setting proper TZ
ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

EXPOSE 8080
VOLUME /opt/temmate-service/logs

WORKDIR /opt/temmate-service/

CMD ["java","-jar","temmate-service-1.0.jar"]