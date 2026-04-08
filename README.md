# Golf Club Management API

## Overview

This project is a Spring Boot REST API for managing members and tournaments in a golf club system. It supports full CRUD operations and relationships between members and tournaments.

---

## Technologies Used

* Java 21
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* JUnit & Mockito
* Postman

---

## Features

### Member Management

* Create member
* Get all members
* Get member by ID
* Delete member
* Update member
* Search by:

    * First name
    * Last name
    * Membership type
    * Phone number
    * Tournament date

### Tournament Management

* Create tournament
* Get all tournaments
* Get tournament by ID
* Delete tournament
* Update tournament
* Add member to tournament
* Search by:

    * Location
    * Member ID

---

## API Base URL

```
http://localhost:8080/api
```

---

## Example Endpoints

### Members

* POST `/members`
* GET `/members`
* GET `/members/{id}`
* PUT `/members/{id}`
* DELETE `/members/{id}`

### Tournaments

* POST `/tournaments`
* GET `/tournaments`
* GET `/tournaments/{id}`
* PUT `/tournaments/{id}`
* DELETE `/tournaments/{id}`

### Relationship

* POST `/tournaments/{tournamentId}/members/{memberId}`

---

## Database Configuration

PostgreSQL is used as the database.

Example `application.yml`:

```
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password:

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

  h2:
    console:
      enabled: true
```

---

## Testing

JUnit and Mockito were used to test the service layer:

* MemberService tests
* TournamentService tests
* Exception handling validation

---

## Postman Testing

All endpoints were tested using Postman however I only included 6 screenshots to show the program works.

### Post Member

![Post Member](DevDocs/Images/Post%20Member.jpg)

### Get Members

![Get Member](DevDocs/Images/Get%20Members.jpg)

### Get By Member Details

![Get By Name](DevDocs/Images/Get%20by%20Name.jpg)

### Post Tournament

![Post Tournament](DevDocs/Images/Post%20Tournament.jpg)

### Add Members To Tournament

![Add Members To Tournament](DevDocs/Images/Add%20members%20to%20tournament.jpg)

### Duplicate Email Check

![Duplicate Email Check](DevDocs/Images/Duplicate%20email%20check.jpg)

---

## Docker Image Running

![Docker Image Running](DevDocs/Images/Docker%20Image.jpg)

## Future Improvements

* Add authentication (Spring Security)
* Improve validation and error handling
* 

---
