### docker container
docker run -d --name virusscan-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=virusscan -v "C:\Training\CheckForViruses\DB_DATA_DOCKER":/var/lib/mysql -p 3306:3306 mysql:latest

### docker container no volume
docker run -d --name virusscan-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=virusscan -p 3306:3306 mysql:latest

### add admin with privileges (use only with volume set)
docker exec -it virusscan-db mysql -u root -p

CREATE USER 'admin'@'%' IDENTIFIED BY 'MySQLSpring';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
