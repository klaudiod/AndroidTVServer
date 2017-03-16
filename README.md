# AndroidTVServer
Rest Services

Author: Klaudio Dervishaj

Developed using:

IDE: IntelliJ 2016.3,

SDK: 1.8,

Application Server: Apache Tomcat 7 or 8,

Frameworks (libs included in \WEB-INF\lib\):

-Spring 3 MVC

-Jersey 1 RESTful Web Services
 
-Hibernate 3 (data persistence)

AndroidTVServer connects to MySQL 'androidtv' schema to perform CRUD. See applicationContext.xml:

Use this to connect to db in server: 

<property name="url" value="jdbc:mysql://38.98.131.162:3306/androidtv?autoReconnect=true" /> 

Otherwise use localhost instead of the ip.

Media content is stored under /opt/androidtv/content. See content.properties:

Use this to connect to store media in server:

serverURL=http://38.98.131.162:64820/
