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
## flyway
https://flywaydb.org/getstarted/firststeps/maven  
集成flyway步骤  
1、将flyway和h2的driver导入pom然后将用户名密码连接的方式写入pom里的configuration里面  
2、git:删除原有的表rm ~/数据库.*，在resources下创建db.migration文件夹，以V1__create_user_table.sql的命名方式创建sql，将原有的创建表的代码写入sql  
3、git:mvn flyway:migrate
## 小匠
https://github.com/codedrinker/community/blob/master/README.md


    
    
    