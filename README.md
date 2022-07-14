#### Running application locally
Run the command below to execute the application

```
./gradlew bootRun
```

#### Integration test

To run the test locally, its necessary run the command below

```
./gradlew test
```

#### Swagger

Link to access swagger locally [link](http://localhost:8080/swagger-ui/index.html).

#### Send request to get the best winners

Run the command below to get the best winners

```
curl --location --request GET 'localhost:8080/movie/premium'
```
