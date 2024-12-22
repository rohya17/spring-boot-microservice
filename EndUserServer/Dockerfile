FROM openjdk:21

ARG jar_file=target/*.jar

COPY ${jar_file} eshop-end-user.jar

ENTRYPOINT ["java", "-jar", "/eshop-end-user.jar"]

EXPOSE 8081