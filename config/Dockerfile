FROM hub.c.163.com/library/java:8-alpine

MAINTAINER wangzhou 413874736@qq.com

ADD target/*.jar  config.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","config.jar"]