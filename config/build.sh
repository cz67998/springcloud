#!C:\Users\Administrator\AppData\Roaming\Microsoft\Windows\Start Menu\Programs bash
mvn clean package -Dmaven.test.skip=true -U
docker build -t springcloud/myConfig .
docker push hub.c.163.com/springcloud/config