#! /bin/bash

if [ $# -lt 6 ]; then
   echo "These args are needed:"
   echo "-n nameOfTheImage"
   echo "-v versionOfTheImage"
   echo "-u user to login in Docker Hub"
   echo "-p password to login in Docker Hub"
   echo "-r Repository to upload the image "
   exit 1
fi

while getopts n:v:u:p:r: option
do
case "${option}"
in
n) NAME=${OPTARG};;
v) VERSION=${OPTARG};;
u) USER=${OPTARG};;
p) PASS=${OPTARG};;
r) REPO=${OPTARG};;
esac
done

# Move un dir up
cd ..
# Clean the target directory
mvn clean 

# Create de package 
mvn package

# Create the docker image
docker build -t $NAME .

# Login in docker to uplad the image 
docker login -u $USER -p $PASS

# Tag the docker image for versioning
docker tag $NAME $USER/$REPO:$VERSION
echo "docker tag $NAME $USER/$REPO:$VERSION"
echo "docker push $USER/$REPO"
docker push $USER/$REPO

exit 0
