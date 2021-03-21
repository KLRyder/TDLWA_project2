Coverage: 90%
# ToDo task list web app

A spring based java application that will act as a REST API to create and track todo tasks. Contains an example webpage make use of the API.

Created with the intent to demonstrate aptitude with springboot, REST APIs and integration testing

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

```
Maven* v3.6 - https://maven.apache.org/
Java 11+ - https://www.oracle.com/uk/java/technologies/javase-downloads.html
Mysql - https://dev.mysql.com/downloads/installer/

* maven is recomended but not required - use of mvnw is avalible.
```

### Installing

1) Fork the project and clone the project to your directory of choice.

```
>git clone https://github.com/KLRyder/TDLWA_project2.git
```

2) Change the Username, Password, and url to a valid configuration in application-test.properties and application-production.properties to a valid user for your Mysql setup.

```
spring.datasource.url=jdbc:mysql://localhost:3306/todolists
spring.datasource.password=root
spring.datasource.username=root
```
Please note that the project currently requires the use AND EXISTENCE of the todolists schema. The schema will be generated automatically when packaging your own copy of this project, but if you only wish to run the project you will need to create a todolist schema first. If you wish to change this you will need to edit test/test-schema.sql appropriately.

3) Ensure that you have installed all by building the project using maven to package the project from its root directory

```
> mvn clean package
> cd target
> java -jar todo_lists-1.0.0.war
```
NOTE: running this will open a chrome browser and run some automated tests, do not close this browser, or the tests will fail, and the maven build will not complete

4) Assuming that everything has run correctly you will be greeted by the following, and the following will be shown

```
> java -jar todo_lists-1.0.0.war

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.4.3)

2021-03-21 13:24:43.708  INFO 5964 --- [   ...
...
...    
```

5) connect to a local copy of the provided webpage at http://localhost:63342/todo_lists/src/frontend/TodoListFrontend/HTML/Index.html 

note, if you wish to move this web page to a new location/server you will need to change the URL used in test/java/com/qa/todo_lists/AcceptanceTests to reflect this.


## Running the tests


### Unit Tests

Unit tests have been provided for the following classes:
```
Controllers
-----------
TaskListController
ToDoTaskController

DOAs
-----------
TaskListDoa
ToDoTask

Model classes
-----------
TaskList
ToDoTask

Mappers
-----------
TaskListMapper
ToDoTaskMapper

Service
-----------
TaskListService
ToDoTaskService
```
### Integration Tests

Integration tests have been provided for the following classes:
```
Controllers
-----------
TaskListController
ToDoTaskController

Service
-----------
TaskListService
ToDoTaskService
```

Additionally, there is the Acceptance test class that will test that the application fulfills all the requirements when accessed from the webpage supplied.

These tests simply ensure that the application and web page work as they should, and help to diagnose where the issue is if any is found.

Provided that you hve your maven installed and configured correctly these tests will run when this is run from the root folder via command line:

```
>mvn clean test
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Kieran Ryder** - [KLRyder](https://github.com/KLRyder)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

