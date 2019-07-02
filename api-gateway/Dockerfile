FROM hub.c.163.com/library/java:8-alpine

MAINTAINER wangzhou 413874736@qq.com

ADD target/*.jar  gateway.jar

EXPOSE 8083

ENTRYPOINT ["java","-jar","gateway.jar"]