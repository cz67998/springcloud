FROM hub.c.163.com/library/java:8-alpine

MAINTAINER wangzhou 413874736@qq.com

ADD server/target/*.jar  order.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","order.jar"]