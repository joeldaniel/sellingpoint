FROM mysql:5.7

MAINTAINER me

ENV MYSQL_DATABASE=sellinpoint-db \
    MYSQL_ROOT_PASSWORD=password

ADD setup.sql /docker-entrypoint-initdb.d

EXPOSE 3306