Simple Springboot application to do simple CRUD operations for a person with minimum validation

1. To create java project,
gradlew eclipse

2. To build the jar,
gradlew clean build

3. To run the jar (\build\libs),
java -jar exercise-0.0.1-SNAPSHOT.jar

The application is running on port 8081, you may update the application.properties under src\main\resources if you need to change it. It contains the database properties too.

Prerequisites to run in docker container
1. You need to have a mysql instance called mysql.
2. Update the database properties in application.properties accordingly.







