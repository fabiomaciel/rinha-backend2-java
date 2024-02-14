#./mvnw clean package -DskipTests && \
docker rmi -f rinha2 && \
docker buildx build --platform linux/arm64/v8 -t rinha2 .
