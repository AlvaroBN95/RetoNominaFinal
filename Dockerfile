FROM mariadb:latest
ENV MYSQL_ROOT_PASSWORD 123
EXPOSE 3306
ADD digimon.sql ./tmp/nomina.sql
RUN /bin/bash -c "/usr/bin/mysqld_safe --skip-grant-tables &" && \
  sleep 5 && \
  mysql -u root -e "CREATE DATABASE nomina" && \
  mysql -u root digimon < /tmp/nomina.sql