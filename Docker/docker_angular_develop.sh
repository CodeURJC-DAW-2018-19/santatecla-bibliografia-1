cd ../Angular

docker build --rm -t application:latest .


docker run --name test1 application

ID=$(docker ps -aqf "name=test1")

echo "El id es : "
echo $ID
docker cp $ID:/application/dist/my-app/ ../src/main/resources/static/

cp -r ../src/main/resources/static/my-app/* ../src/main/resources/static/new/

rm -rf ../src/main/resources/static/my-app

docker container rm $ID