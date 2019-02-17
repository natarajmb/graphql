FROM openjdk:8-jdk-alpine
ADD build/libs/rest-service-app.jar app.jar
EXPOSE 8080
CMD java -Dhttps.protocols=TLSv1,TLSv1.1,TLSv1.2 -jar /app.jar