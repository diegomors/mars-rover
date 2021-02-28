# Mars Rover

A solution for Mars Rover problem.

## Setup

- [Install JDK 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
- [Install Maven 3.6](https://maven.apache.org/install.html)

## Command-line

- Clean and package
    ```
    $ mvn clean package
    ```

- Run tests
    ```
    $ mvn test
    ```
    ![](https://github.com/diegomors/mars-rover/blob/master/images/tests.gif)

    ![](https://github.com/diegomors/mars-rover/blob/master/images/tests2.gif)

- Run with args
    ```
    $ mvn exec:java -Dexec.mainClass=marsrover.App -Dexec.args="C:\input.txt"
    ```
    ![](https://github.com/diegomors/mars-rover/blob/master/images/run.gif)

## Project Architecture

```
{project}
├── src\main\java
|   ├── entities
|   |   ├── {Class}.java
|   ├── exceptions
|   |   ├── {Class}.java
|   ├── helpers
|   |   ├── {Class}.java
|   ├── services
|   |   ├── {Class}.java
|   ├── App.java
├── src\test\java
|   ├── AppTest.java
└── pom.xml
```

**Entities**: POJO classes and enums to represent data model.

**Exceptions**: Custom exceptions to represent business problem.

**Helpers**: Auxiliary classes to read file and input convert.

**Services**: Business rules and exception handle.

**App.java**: The main file to run without tests.

**AppTest.java**: The main file to run tests.

**pom.xml**: The dependency manager file (Maven).