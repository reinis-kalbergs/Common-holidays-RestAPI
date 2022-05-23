# Common holidays

REST API using Java, Spring Boot.

---

Returns a list of common holidays for both given countries in a given year. Where the source public holidays is from https://date.nager.at/ .

### Running

```console
mvnw spring-boot:run
```

### Documentation (when the application is running), also available at:

```
localhost:8080/swagger-ui/index.html
```

### Available endpoints

```
localhost:8080/api/flights/{countryCode1}/{countryCode2}/{year} [GET]
```
