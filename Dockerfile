# 添加 Java 8 镜像来源
FROM openjdk:8-jdk-alpine

# 添加参数
ARG JAR_FILE='hello_jenkins-0.0.1.jar'

# 添加 Spring Boot 包
ADD target/${JAR_FILE} app.jar

EXPOSE 8452

# 执行启动命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xms128m","-Xmx128m","-jar","/app.jar"]