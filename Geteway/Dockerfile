FROM openjdk:21

ARG jar_file=target/*.jar

COPY ${jar_file} gatewayserver.jar

ENTRYPOINT ["java","-jar","gatewayserver.jar"]

EXPOSE 8080