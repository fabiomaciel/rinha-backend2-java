FROM amazoncorretto:21-alpine3.19
LABEL authors="fabiomaciel"

ADD target/*.jar app.jar

CMD java -jar -Xms64m -Xmx256m -Dserver.port=$PORT /app.jar