1. creating docker file
- create a file with name 'Dockerfile' without extension in directory where src folder is situated
- add code to build image 

```
defining base image
FROM openjdk:21

#defining jar
ARG jar_file=target/*.jar

#copying jar to image
COPY ${jar_file} serviceregistry.jar

#command to run jar
ENTRYPOINT ["java","-jar","/serviceregistry.jar"]

#port number where application will run
EXPOSE 8761 

```
- in same directory run command to build image

```
docker build -t rohya1995/serviceregestry:latest .

```
- '-t' for giving image tag name 
- 'rohya1995' docker account username
- 'serviceregestry:latest' image tag name
- '.' to represent current directory

2. running docker image
- check docker image id using command 'docker images'
- run using command 'docker run -d -p dport:lhport imageid -name container_name'
- -d for detached mode running
- -p for mapping port docker 'dport' to localhost port 'lhport'
- -name for specifying container name
- example: docker run -d -p 8761:8761 564cbaacee02 -name serviceregestry