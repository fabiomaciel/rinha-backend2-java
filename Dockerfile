FROM amazoncorretto:21-alpine3.19
LABEL authors="fabiomaciel"

ENV PORT 8080
ADD target/rinha2-0.0.1.jar /app.jar

CMD java -jar -Xms64m -Xmx256m -Dserver.port=$PORT /app.jar