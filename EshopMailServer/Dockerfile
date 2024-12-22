FROM openjdk:21

ARG jar_file=target/*.jar

COPY ${jar_file} mailserver.jar

ENTRYPOINT ["java","-jar","/mailserver.jar"]

EXPOSE 8083