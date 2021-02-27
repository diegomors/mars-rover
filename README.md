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

- Run with args
    ```
    $ mvn exec:java -Dexec.mainClass=App -Dexec.args="C:\input.txt"
    ```

## Project Architecture

```
{project}
├── src\main\java
|   ├── entities
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

**Entities**: POJO classes to represent data model.

**Helpers**: Auxiliary classes to read file and input convert.

**Services**: Business rules and exception handle.

**App.java**: The main file to run without tests.

**AppTest.java**: The main file to run tests.

**pom.xml**: The dependency manager file (Maven).