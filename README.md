# Project SUPER USER

*A simple project to manage users and profiles.*

## Description

The propose of this project is improve some knowledges and do some experiences

## Getting Started

to run the project:

* Using docker:

To build the image:

```sh
 docker build -t superuser . 
```
Then, run:
	
```sh
 run --rm -p 8081:8081 -it superuser:latest 
```

**The build will takes some time to build, because I decide to crate a building step at dockerfile. This is not the best approach. Normally, in a regular pipeline you need to have a step to build then, in your dockerfile, we need to have just the 'run' step. But, for this study propose, I see no problem

* Without docker	

```sh
 mvn spring-boot:run
```

 http://localhost:8081/superuser/api/swagger-ui.html
 
 [![Run in Postman](https://run.pstmn.io/button.svg)](https://documenter.getpostman.com/view/3569107/UUxwC8j2)

## About

  The main idea is to create some endpoints to create, delete, update and retrieve profiles and users
  
  I decide to use Spring Boot, Data and Rest to build the project.
  
  Pagination was implemented.
  
  DTO Pattern was implemented. The achievement was do not show sensitive informations to other layers or clients. 
  At this moment, the only information omitted from client is ID.
  
  I decide to put some errors handles and bean validation
  
  HATEOAS was implement to make the APIs RestFul
  


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

