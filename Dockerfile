FROM eclipse-temurin
ADD target/*.jar app.jar
EXPOSE 8081
CMD java -jar *.jar