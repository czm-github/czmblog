## spring文档
https://spring.io/guides
## springboot快速搭建
https://spring.io/guides/gs/serving-web-content/
## git、github
https://github.com/
## git登录之Building OAuth and okhttp
https://developer.github.com/apps/  
https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/  
https://square.github.io/okhttp/
## 数据库的持久化 h2+mybatis+java
http://www.h2database.com/html/main.html
## flyway数据库版本迁移工具
https://flywaydb.org/getstarted/firststeps/maven  
集成flyway步骤  
1、将flyway和h2的driver导入pom然后将用户名密码连接的方式写入pom里的configuration里面  
2、git:删除原有的表rm ~/数据库.*，在resources下创建db.migration文件夹，以V1__create_user_table.sql的命名方式创建sql，将原有的创建表的代码写入sql  
3、git:mvn flyway:migrate这是flyway的运行代码，用来更新数据库sql
## 小匠
https://github.com/codedrinker/community/blob/master/README.md

mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate

##部署
###依赖
- git  
- jdk  
- maven  
- mysql
###步骤
- yum update  
- yum install git  
- mkdir App  
- cd App  
- git clone https://github.com/czm-github/czmblog.git  
- yum install maven  
- mvn -v  
- mvn clean compile package  
- cp src/main/resources/application.properties src/main/resources/application-production.properties  
- vim src/main/resources/application-production.properties  
- mvn package  
- java -jar -Dspring.profiles.active=production target/czmblog-0.0.1-SNAPSHOT.jar  
- ps -aux | grep java  
- git pull  

    
    