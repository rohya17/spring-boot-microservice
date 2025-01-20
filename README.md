# Microservice Sample Project

Hey there !,
my name is Rohit, and I am software engineer.

this repository is only for work sample purpose. this repository contains one microservice project which is only created for showcasing how to use different dependencies of maven to create a microservice project. There no business logic behind the code, API's are written only to demonstrate how it can be written. Further i will keep adding multiple things that are used in microservice.

Till now these are the implemented technologies in this project: 
1. Spring cloud Eureka client
2. Spring cloud Eureka server
3. Spring cloud Gateway 
4. Spring validation
5. Distributed tracing system
    - zipkin
    - micrometer
6. Swagger API documentation
7. Email service using java email and smtp
8. Circuit breakers
9. Docker for microservice

# How to run project
1. Pull or download a zip of code - then extract and build
2. Database used is postgres sql, download database backup file and restore it to blank database named 'eshop'
3. Change database credentials according to you database in application properties of applications
4. Maven build each microservice and generate jar file
5. Run service register first and observe it on localhost 8761
6. Run config server so that other services can start
7. Run zipkin jar and observe it on localhost 9411
8. Run Gateway application and then run other applications
9. Swagger UI can be observed on IP of application + /swagger-ui/index.html#/, ex. http://localhost:8080/swagger-ui/index.html#/
10. All API's can be access through application gateway IP address.
11. Now as you access API's you can find traces on localhost 9411 zipkin UI
12. Or use dockerfile and docker compose to run service on docker, by changing account credentials to your own creds
