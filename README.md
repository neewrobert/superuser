# Project SUPER USER

*A simple project to manage users and profiles.*

## Description

The propose of this project is improve some knowlegements and do some experiences

## Getting Started

to run the project:

* Using docker:

To build the image:

	```
	docker build -t superuser . 
	```
Then, run:
	
	```
	run --rm -p 8081:8081 -it superuser:latest 
	```

**The build will takes some time to build, because I decide to crate a building step at dockerfile. This is not the best approach. Normally, in a regular pipeline you need to have a step to build then, in your dockerfile, we need to have just the 'run' step. But, for this study propose, I see no problem

* Without docker	

```sh
 mvn spring-boot:run
```

 http://localhost:8081/superuser/api/swagger-ui.html

## About

  The main idea is to create some endpoints to create, delete, update and retrieve profiles and users
  
  I decide to use Spring Boot, Data and Rest to build the project.
  
  Pagination was implemented.
  
  DTO Pattern was implemented. The achievement was do not show sensitive informations to other layers or clients. 
  At this moment, the only information omitted from client is ID.
  
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

* *Change the profile of an user - Done*
* *Add a profile to a user who was created without it - Done*
* *Docker - Done*
* Unit tests
* Integration Tests
* Logs



  


## Authors

 Newton Santos   


## Version History

* 0.1
    * Initial Release
    
* 0.2
    * Add docker run commands
    * Update the next features

