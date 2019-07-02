FROM hub.c.163.com/library/java:8-alpine

MAINTAINER wangzhou 413874736@qq.com

ADD user-server/target/*.jar  user.jar

EXPOSE 8085

ENTRYPOINT ["java","-jar","user.jar"]