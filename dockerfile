#Conseguir la imagen de mariadb
FROM mariadb

#Contraseña de rooot
ENV MARIADB_ROOT_PASSWORD=root

#nombre de la base de datos
ENV MARIADB_DATABASE=nominas

#usuario para el usuario de la base  de datos
ENV MARIADB_USER=alvaro

#contraseña usuario base de datos.
ENV MARIADB_PASSWORD=alvaro

#base de datos a copiar y a ejecutar.
COPY nomina.sql /docker-entrypoint-initdb.d



