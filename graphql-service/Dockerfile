FROM openjdk:8-jdk-alpine
ADD build/libs/graphql-service-app.jar app.jar
EXPOSE 2020
CMD java -Dhttps.protocols=TLSv1,TLSv1.1,TLSv1.2 -jar /app.jar