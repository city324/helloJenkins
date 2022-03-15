mvn clean

mvn package -DskipTests

docker rmi -f lgj/webdemo:1.0

mvn dockerfile:build

docker images