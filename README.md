## XMEME

XMEME is an application, currently work in progress, built with Java SpringBoot and MongoDB. It will ben used to store meme information in Database. It contains 3 APIs for now:

1) POST a request - save a new meme. check s fro duplicates
2) GET by ID
3) GET top 100


./gradlew bootrun


GET
curl --location --request GET 'http://localhost:8081/memes/'


POST

curl --location --request POST 'http://<Server_URL>/memes/' \

--header 'Content-Type: application/json' \

--data-raw '{

"name": "xyz",

"url": "https://cwod-assessment-images.s3.ap-south-1.amazonaws.com/images/130.png",

"caption": "This is a meme"

}'



This file can be used as a template for initializing and running spring projects.

What's included: 
1. Gradle file created from start.spring.io
2. Plugins for Spotbugs, Checkstyle and Jacoco included
3. Other dependencies like Mongo, MySql and redis.
4. Dockerfile to start mongo server and run the spring boot application within.

Usage - 

1. To build the repository - 

From the repository root, 

1. run `./gradlew build test`run the build
2. run `./gradlew bootjar` to create executable jar. The jar will be located inside build directories.

To run inside docker container, use below commands

To build docker image, use the command below - `docker build -t your_tag_name  .`

To run the generated container, use this command - `docker run -p8080:8080 your_tag_name`. This will run the server on 8080 port.. You can change the ports as per your needs. 


License - 
While this repository is licensed under APACHE 2.0 license, It is mandatory for users to share the readme.md and License file along with the changes they do in the contents.
