docker ps -a

docker stop webdemo-8452
docker rm -f webdemo-8452
docker run -d --name webdemo-8452  -p 8452:8452 lgj/webdemo:1.0

docker ps -a