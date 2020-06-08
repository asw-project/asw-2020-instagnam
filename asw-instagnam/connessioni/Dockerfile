########################################################
# Dockerfile per l'applicazione connessioni 
########################################################

FROM openjdk:8-jdk

RUN apt-get update && apt-get install -y curl

ADD build/libs/connessioni.jar connessioni.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128m", "-Xms128m", "-jar", "connessioni.jar"]

HEALTHCHECK --start-period=30s --interval=10s --retries=10 CMD curl -f http://localhost:8080/actuator/health || exit 1