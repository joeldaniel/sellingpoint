FROM tomcat:alpine
COPY tomcat-users.xml /usr/local/tomcat/conf
COPY ./target/selling-point-service.war /usr/local/tomcat/webapps/
CMD catalina.sh run