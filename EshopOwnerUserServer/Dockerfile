FROM openjdk:21

ARG jar_file=target/*.jar

COPY ${jar_file} owneruserserver.jar

ENTRYPOINT ["java", "-jar", "owneruserserver.jar"]

EXPOSE 8082