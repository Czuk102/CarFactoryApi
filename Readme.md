# Car Factory API 
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) 
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
***
Car Factory APi is my portfolio project, created as a showcase of my skills in building sturdy and robust web applications.
It is a REST API for managing car factory. It allows to create, read, update and delete cars, engines and car parts.
## Getting started
***
### Setting up keys
To run this application first, you need to generate RSA Keypair and put it in certs folder.
I recommend to install [OpenSSL](https://wiki.openssl.org/index.php/Binaries), becouse of specific  PEM-encoded PKCS#8 format
needed by Spring Security JWT. 
To generate keys, navigate to `src/main/resources/certs`, and run in terminal: 
```
openssl genrsa -out keypair.pem 2048
```
that generates 2048 bit RSA keypair
```
openssl rsa -in keypair.pem -pubout -out public.pem
```
extract pulic key from keypair 
```
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
````
creates private key in needed PKCS#8 format.

### Run App
***
To run this application, you can use Docker, which sets up the MySQL database and downloads all the necessary dependencies. Follow these steps:
1. First, we need to create our JAR package. Go to the project root and run:
```
./mvnw clean package
```
2. Once the package is created, run the following command:
```
docker-compose up --build
```
Congratulations! The Car Factory API and the associated database should now be up and running.

### Swagger UI
***
To see the documentation of the API, go to:
```
http://localhost:8081/swagger-ui.html
```



