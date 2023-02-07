mvn clean package -DskipTests
docker login registry.gitlab.com
docker build -t registry.gitlab.com/zorgundostu/mp-location-service:arm64 . --platform linux/arm64
docker push registry.gitlab.com/zorgundostu/mp-location-service:arm64
