# Project SUPER USER

A simple project to manage users and profiles.

## Description

The propose of this project is improve some knowlegements and do some experiences

## Getting Started

to run the project:
```
 mvn spring-boot:run
```

 http://localhost:8081/superuser/api/swagger-ui.html

## About

  The main Ideia is to create some endpoints to create, delete, update and retrive proflies and users
  
  I decide to use Spring Boot, Data and Rest to build the project.
  
  Pagination was implemented.
  
  DTO Pattern was implemented. The achieviment was do not show sensitive informatios to other laywers or clients. 
  At this moment, the only information omited from client is ID.
  
  I decide to put some errors handles and bean validation
  
  HATEOAS was implement to make the APIs RestFul
  
## Playground

At first you will need to create a profile

### Profile

`CREATE PROFILE - POST` [/profiles](#post) <br/>
  
```
{
    "profileType": "manager"
}
```

`GET PROFILE - GET` [/profiles/{profileType}](#post) <br/>
`DELETE PROFILE - DELETE` [/profiles/{profileType}](#post) <br/>
`GET ALL PROFILE (paginated) - GET` [/profiles?page=3&size=1](#post) <br/>

### Users

`CREATE USER - POST` [/users](#post) <br/>
  
```
{
    "name": "Jose Doe",
    "birthDate": "1991-07-28",
    "profile": {
        "profileType": "manager"
    },
    "phoneNumber": "111",
    "email": "jose@gmail.com"
}
```

`GET USER - GET` [/users/{email}](#post) <br/>
`DELETE USER - DELETE` [/users/{email}](#post) <br/>
`GET ALL USERS (paginated) - GET` [/users?page=3&size=1](#post) <br/>
`GET ALL USERS by PROFILE - GET` [/profiles/{profileType}/users](#post) <br/>


## NEXT FEATURES

* Change the profile of an user
* Add a profile to a user who was created without it
* Build Unit tests
* Integration Tests

  


## Authors

 Newton Santos   


## Version History

* 0.1
    * Initial Release

