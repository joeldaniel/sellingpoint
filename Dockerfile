FROM tomcat:alpine
COPY ./target/selling-point-service.war /usr/local/tomcat/webapps/
CMD catalina.sh run