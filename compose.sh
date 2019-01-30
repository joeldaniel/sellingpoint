cd docker
docker-compose up -d
docker cp ../target/selling-point-service.war tomcat:/usr/local/tomcat/webapps/
cd ..