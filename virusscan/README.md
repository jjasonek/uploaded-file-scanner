### docker container for MySQL
docker run -d --name virusscan-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=virusscan -v "C:\Training\CheckForViruses\DB_DATA_DOCKER":/var/lib/mysql -p 3306:3306 mysql:latest

### docker container no volume
docker run -d --name virusscan-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=virusscan -p 3306:3306 mysql:latest

### add admin with privileges (use only with volume set)
docker exec -it virusscan-db mysql -u root -p

CREATE USER 'admin'@'%' IDENTIFIED BY 'MySQLSpring';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;


### docker container for RabbitMQ
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management

Then we can login to RabbitMQ console here: http://localhost:15672/

### docker for ClamAv 
docker pull clamav/clamav


## TO DO LIST
- Use more unique file identifier like UUID. FileName is not sufficient. **OK**
- Use transactions for file upload. **OK**
- Use transaction for file status update. **OK**
- Add actual virus scan functionality.
- Dockerize the application including MySQL and RabbitMQ.
- Try some more appropriate date type for the file (blob/clob).
- Try to work with file without "touching" it. (serializing / deserializing).
### optional
- Use MongoDB instead of MySQL
- Use Kafka instead of RabbitMQ
