cd ../Angular

docker build --rm -t application:latest .

docker run --name test1 application
ID=$(docker ps -aqf "name=test1")

cd ..

echo "El id es : "
echo $ID

docker cp $ID:/application/dist/my-app/ src/main/resources/static/

echo "Check of exist the destination folder in resources/static -> new/"
if [ -d "src/main/resources/static/new/" ];
then 
    echo "The folder exists, delete, and create a empty one"
    rm -rf src/main/resources/static/new/
    cd src/main/resources/static
    mkdir new
    cd -
    cp -r src/main/resources/static/my-app/* src/main/resources/static/new/
else
    echo " The folder doesn't exist. Created"
    cd src/main/resources/static
    mkdir new
    cd -
    cp -r src/main/resources/static/my-app/* src/main/resources/static/new/
fi
#cp -r src/main/resources/static/my-app/* src/main/resources/static/new/

rm -rf src/main/resources/static/my-app

docker container rm $ID