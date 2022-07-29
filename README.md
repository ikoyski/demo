# Spring Boot Demo App

This is a demo app practicing spring boot.

Click on the downloaded Lombok-{version}.jar and select your IDE to install lombok
Restart the IDE after successful Lombok installation

### Pre-commit checks

In Windows run

```
mvnw.cmd clean verify
```

In Linux/MacOs run

```
./mvnw clean verify
```

This should build and run all the tests in the same way as on the continuous integration server.  If the build is successful, you can commit to git and push to Git.

### Run the app

In Windows run

```
cd /path/of/the/project/
mvnw.cmd clean package
java -jar target/*.jar
```
In Linux/MacOs run

```
cd /path/of/the/project/
./mvnw clean package
java -jar target/*.jar
```

## Smoke test services

There is a `/health` endpoint that provides basic information about the application’s health:

```
curl http://localhost:8080/actuator/health
```

The endpoint should display the following -

```
{
    "status": "UP"
}
```

The status will be UP as long as the application is healthy. It will show DOWN if the application gets 
unhealthy due to any issue like connectivity with the database or lack of disk space etc. 

The build and version information can be checked by calling the `/info` endpoint.  This allows checking of the git tags, maven version, build date etc.

```
curl http://localhost:8080/actuator/info
```

This endpoint will return something like the following.

```
{
  "git": {
    "commit": {
      "id": "2028dd7a66f135d2fdb557e03349cc982053f9bd"
    },
    "branch": "master"
  }
}
```

Hit the functional endpoint (**Server**)

```
curl --location --request GET 'http://localhost:8080/api/v1/students'
```

This should return something like

```
[
  {
    "id": 1,
    "name": "Peter",
    "email": "peter@gmail.com",
    "dob": "2000-01-05",
    "age": 22
  },
  {
    "id": 2,
    "name": "James",
    "email": "james@gmail.com",
    "dob": "2004-01-05",
    "age": 18
  }
]
```

## Configuration file

application.properties

## Swagger / Open API

openapi.yml

## Sonarcloud.io

https://sonarcloud.io/summary/overall?id=ikoyski_demo

## DB

Using H2 In-memory DB
H2 console URL is http://localhost:8080/h2-console
