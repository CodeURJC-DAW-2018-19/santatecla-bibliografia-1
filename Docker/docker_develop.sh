#! /bin/bash

cd ..
# First of all we clean the target directory
mvn clean 

# Build the pakcage
mvn package

# Generate the image using the Dockerfile 
docker build -t santatecla/bibliografia .


