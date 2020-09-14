#!/bin/bash
docker stop users-service
docker rm users-service
docker image rmi users_service:latest
mvn clean package
docker-compose up -d
mvn liberty:dev
