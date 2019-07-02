FROM hub.c.163.com/library/java:8-alpine

MAINTAINER wangzhou 413874736@qq.com

ADD server/target/*.jar  product.jar

EXPOSE 8084

ENTRYPOINT ["java","-jar","product.jar"]