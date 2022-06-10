FROM mariadb

ENV MARIADB_ROOT_PASSWORD=root

ENV MARIADB_DATABASE=nominas

ENV MARIADB_USER=alvaro

ENV MARIADB_PASSWORD=alvaro

COPY nomina.sql /docker-entrypoint-initdb.d



