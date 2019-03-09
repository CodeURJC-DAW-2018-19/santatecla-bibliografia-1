# SantaTecla-Bibliografia-1 - API REST Documentation

## About our API

## How to use our API
1. Download [Postman](https://www.getpostman.com/).
2. Run the applicattion as we explained in the readme.
3. Open Postman.
4. Go to File/Settings and turn off the SSL certificate validation.
5. Now you can test the Api with postman
## API requests

### Resources
The resource API has GET, POST, PATCH and DELETE methods.

**GET is permited for all users. POST, PATCH and DELETE Are only allowed for the admin.**

To start using the api use this url
http: // localhost:8443 followed by the containt request URL.

**All API queries have been preceded by /api**

## Authentication

The basic authentication implemented can be tested in postman using the Autorization form, showed in the image below.

![Image of postman](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/assets/img/icons/CapturaPostman.PNG)


For the admin role you must use the following params:

**Username**: admin

**Password**: admin


And for user/student role:

**Username**: juan

**Password**: 4321


### GET requests

Every user is allowed to do GET requests to the API

#### GET all resources
It works the same for authors, themes and quotes, and show all the content of the specified resource.

* ##### URL:

	< /book >

* ##### Method:

	`GET`
	
* ##### Success Response:
  	```
	{
        "id": 92,
        "title": "Nacidos de la bruma",
        "publishDate": "",
        "nameEdit": "",
        "urlEdit": "",
        "urlImgCoverPage": "",
        "urlImgEdit": "",
        "imgId": 4
    },
    {
        "id": 95,
        "title": "El año de los delfines",
        "publishDate": "",
        "nameEdit": "",
        "urlEdit": "",
        "urlImgCoverPage": "",
        "urlImgEdit": "",
        "imgId": 5
    },
    {
        "id": 97,
        "title": "Refranero",
        "publishDate": "",
        "nameEdit": "",
        "urlEdit": "",
        "urlImgCoverPage": "",
        "urlImgEdit": "",
        "imgId": -1
    }
	```
  
* ##### Error response:

	**Code**: 404 NOT FOUND
	
#### GET one resource

It works the same for authors, themes and quotes, and show in detail the resource represented by the {id}.


* ##### URL:

	< /book/{id} >

* ##### Method:

	`GET`
	
* ##### Success Response:
  	```
	{
    "id": 92,
    "title": "Nacidos de la bruma",
    "publishDate": "",
    "nameEdit": "",
    "urlEdit": "",
    "urlImgCoverPage": "",
    "urlImgEdit": "",
    "imgId": 4,
    "theme": {
        "id": 91,
        "name": "Vida"
    },
    "citation": [
        {
            "id": 93,
            "text": "La Ilusión despierta el empeño y solamente la paciencia lo termina.",
            "textAux": null
        }
    ],
    "author": {
        "id": 107,
        "name": "Fernando Esteso",
        "urlImage": "https://img.bekia.es/celebrities/th/2000/2576.jpg",
        "birthDate": "1945-2-16",
        "deathDate": "",
        "bornPlace": "Zaragoza, España",
        "urlMap": "https://www.google.es/maps/place/Zaragoza/@41.6516859,-0.9300003,13z/data=!3m1!4b1!4m5!3m4!1s0xd5914dd5e618e91:0x49df13f1158489a8!8m2!3d41.6488226!4d-0.8890853",
        "imgId": 1
    }
}
	```
  
* ##### Error response:

	**Code**: 404 NOT FOUND
	
