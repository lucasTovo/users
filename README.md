# Orion User Service

Orion User Service is a microservice

## Run with Docker Compose

The easer way to install and execute the Orion User Service is to use docker-composer. Once Docker is installed, you can run the bellow command in the  :

    docker-compose up -d

Note: Default database root and password is: orion-user-service

## Docker image

To create a Docker image for Orion User Service:

    mvn clear package

    docker build -t orion-user-image .

    docker run -d --name orion-user-service -p 9080:9080 -p 9443:9443 orion-user-image

### Orion User dev mode

During development, you can use Liberty's development mode (dev mode) to code while observing and testing your changes on the fly.

    mvn clear package
    mvn liberty:dev
<!--
### JWT Auth

Have a look at the **TestSecureController** class (main application) which calls the protected endpoint on the secondary application.
The **ProtectedController** contains the protected endpoint since it contains the _@RolesAllowed_ annotation on the JAX-RS endpoint method.

The _TestSecureController_ code creates a JWT based on the private key found within the resource directory.
However, any method to send a REST request with an appropriate header will work of course. Please feel free to change this code to your needs.
-->
