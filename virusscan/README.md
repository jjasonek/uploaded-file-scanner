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

### postman collection:
"C:\Training\CheckForViruses\MockServerExpectations\VirusScan.postman_collection.json"

### set expectations
This must be done before we start upload files for scanning.
PUT localhost:1080/mockserver/expectation
{
    "id": "mockCleanFile",
    "priority": 0,
    "httpRequest": {
        "method": "POST",
        "path": "/api/scan",
        "body": {
            "type": "BINARY",
            "string": "some text"
        }
    },
    "httpResponse": {
        "delay": {
            "timeUnit": "SECONDS",
            "value": 5
        },
        "statusCode": 200,
        "body": {
            "result": "clean"
        }
    }
}

PUT localhost:1080/mockserver/expectation
{
    "id": "mockInfectedFile",
    "priority": 0,
    "httpRequest": {
        "method": "POST",
        "path": "/api/scan",
        "body": {
            "type": "BINARY",
            "string": "infected file"
        }
    },
    "httpResponse": {
        "delay": {
            "timeUnit": "SECONDS",
            "value": 5
        },
        "statusCode": 200,
        "body": {
            "result": "infected"
        }
    }
}


## TO DO LIST
- Use more unique file identifier like UUID. FileName is not sufficient. **OK**
- Use transactions for file upload. **OK**
- Use transaction for file status update. **OK**
- Add actual virus scan functionality. **OK with mock**
- Dockerize the application including MySQL and RabbitMQ.
- Try some more appropriate date type for the file (blob/clob). **OK**
- Try to work with file without "touching" it. (serializing / deserializing). **Not sure**
### optional
- Use MongoDB instead of MySQL
- Use Kafka instead of RabbitMQ
