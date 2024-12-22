FROM openjdk:21

ARG jar_file=/*.jar

COPY ${jar_file} zipkin.jar

ENTRYPOINT ["java", "-jar", "/zipkin.jar"]

EXPOSE 9411