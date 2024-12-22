FROM openjdk:21

ARG jar_file=target/*.jar

COPY ${jar_file} serviceregistry.jar

ENTRYPOINT ["java","-jar","/serviceregistry.jar"]

EXPOSE 8761