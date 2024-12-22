FROM openjdk:21

ARG jar_file=target/*.jar

COPY ${jar_file} configserver.jar

ENTRYPOINT ["java","-jar","/configserver.jar"]

EXPOSE 8084