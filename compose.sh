cd docker
docker-compose up -d
sleep 30
docker cp ../target/selling-point-service.war tomcat:/usr/local/tomcat/webapps/
cd ..