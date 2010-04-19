Requirements
------------
*	[Java] (http://java.com/en/download/index.jsp)
*	[Mysql](http://www.mysql.com/downloads/mysql/)
*	[Maven](http://maven.apache.org/download.html)

Installation
------------
*  create a database named as `socialsite`  	
*  change the username and password in src/main/resources/application.properties 	
*  Navigate to the root folder and run the following command ` mvn -Dtest=LoadData test ` . This 
   will create all the necessary table in the database and load some sample data in the database.
*  Now run the following command ` mvn jetty:run ` and point your browser to http://localhost:8080/socialsite/

usernames : user1 , user2 .. , admin1 , admin2 .. , staff1 , staff2 ..
password  : password