# Virus scan for uploaded file.

## Optionally start DB setting permanent data storage

### docker container for MySQL
docker run -d --name virusscan-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=virusscan -v "C:\Training\CheckForViruses\DB_DATA_DOCKER":/var/lib/mysql -p 3306:3306 mysql:latest

### add admin with privileges (use only with volume set)
docker exec -it virusscan-db mysql -u root -p

CREATE USER 'admin'@'%' IDENTIFIED BY 'MySQLSpring';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;


## Manual start of services

### 1. docker MySQL no volume
docker run -d --rm --name virusscan-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=virusscan -p 3306:3306 mysql:latest

### 2. docker container for RabbitMQ
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management
Then we can login to RabbitMQ console here: http://localhost:15672/

### 3. docker for MockServer loading expectations
docker run --rm --name virus_scan_mockserver -v C:\Training\CheckForViruses\MockServerData:/config 
-e MOCKSERVER_INITIALIZATION_JSON_PATH="/config/initializerJson.json" -p 1080:1080 mockserver/mockserver


## Run by docker-compose

### 1. dockerize application virusscan
**mvn compile jib:dockerBuild**
From teh directory where pom.xml is.
Then:
docker ps -a
REPOSITORY                                 TAG            IMAGE ID       CREATED         SIZE
...
virusscan                                  s1             56540a4812c6   55 years ago    361MB

I'm not saving this image to Docker Hub, so before **docker compose up**, the image has to be build using command above.

### 2. docker-compose up
cd ..\docker-compose\default\
docker-compose up


## Additional information  

### postman collection:
~\CheckForViruses\MockServerExpectations\VirusScan.postman_collection.json

### optionally set expectations for MockServer
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
