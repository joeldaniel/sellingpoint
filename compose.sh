cd docker
docker-compose down
sleep 20
docker-compose up -d
sleep 20
docker cp ../target/selling-point-service.war tomcat:/usr/local/tomcat/webapps/
cd ..