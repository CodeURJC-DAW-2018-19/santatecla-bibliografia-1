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

**All API queries are preceded by /api**

## Authentication

The basic authentication implemented can be tested in postman using the Autorization form, showed in the image below.

![Image of postman](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/assets/img/icons/CapturaPostman.PNG)


For the admin role you must use the following params:

**Username**: admin

**Password**: admin


And for user/student role:

**Username**: juan

**Password**: 4321

### Login

* ##### URL:

	< /login >

* ##### Method:

	`GET`
	
* ##### Success Response:
  ```
	{
    "id": 109,
    "name": "juan",
    "roles": [
        "ROLE_STUDENT"
    ]
	}
  ```
  
* ##### Error response:

	**Code**: 401 UNAUTHORIZED
	
### Logout

* ##### URL:

	< /logout >

* ##### Method:

	`GET`
	
* ##### Success Response:

  	```
	true
	```
  
* ##### Error response:

	**Code**: 404 NOT FOUND
	
### GET requests

Every user is allowed to do GET requests to the API

#### GET all books

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
	
#### GET one book

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
	

#### GET all themes

* ##### URL:

	< /theme >

* ##### Method:

	`GET`
	
* ##### Success Response:
  	```
    {
        "id": 76,
        "name": "Amor"
    },
    {
        "id": 91,
        "name": "Vida"
    },
    {
        "id": 94,
        "name": "Clasico"
    }

	```
  
* ##### Error response:

	**Code**: 404 NOT FOUND
		
#### GET one theme

* ##### URL:

	< /theme/{id} >

* ##### Method:

	`GET`
	
* ##### Success Response:
  	```
    {
    "id": 76,
    "name": "Amor",
    "books": [
        {
            "id": 77,
            "title": "Palabras Radiantes",
            "publishDate": "",
            "nameEdit": "",
            "urlEdit": "",
            "urlImgCoverPage": "",
            "urlImgEdit": "",
            "imgId": 3,
            "citation": [
                {
                    "id": 78,
                    "text": "El misterio de la vida no es un problema a resolver, sino una realidad a experimentar",
                    "textAux": null
                },
		  "author": {
                "id": 106,
                "name": "Brandon Sanderson",
                "urlImage": "https://www.goodreads.com/photo/author/38550.Brandon_Sanderson",
                "birthDate": "1975-12-19",
                "deathDate": "",
                "bornPlace": "Lincoln, Nebraska",
                "urlMap": "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d96647.87182448553!2d-96.76076790212979!3d40.80058782506782!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8796be59ca561265%3A0x633a859b1fd5deb9!2sLincoln%2C+Nebraska%2C+EE.+UU.!5e0!3m2!1ses!2ses!4v1551055373899",
                "imgId": 0
            }
	```
  
* ##### Error response:

	**Code**: 404 NOT FOUND
	
	
		
#### GET all authors

* ##### URL:

	< /author >

* ##### Method:

	`GET`
	
* ##### Success Response:
  	```
       {
        "id": 106,
        "name": "Brandon Sanderson",
        "urlImage": "https://www.goodreads.com/photo/author/38550.Brandon_Sanderson",
        "birthDate": "1975-12-19",
        "deathDate": "",
        "bornPlace": "Lincoln, Nebraska",
        "urlMap": "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d96647.87182448553!2d-96.76076790212979!3d40.80058782506782!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8796be59ca561265%3A0x633a859b1fd5deb9!2sLincoln%2C+Nebraska%2C+EE.+UU.!5e0!3m2!1ses!2ses!4v1551055373899",
        "imgId": 0
    },
    {
        "id": 107,
        "name": "Fernando Esteso",
        "urlImage": "https://img.bekia.es/celebrities/th/2000/2576.jpg",
        "birthDate": "1945-2-16",
        "deathDate": "",
        "bornPlace": "Zaragoza, España",
        "urlMap": "https://www.google.es/maps/place/Zaragoza/@41.6516859,-0.9300003,13z/data=!3m1!4b1!4m5!3m4!1s0xd5914dd5e618e91:0x49df13f1158489a8!8m2!3d41.6488226!4d-0.8890853",
        "imgId": 1
    },
    {
        "id": 108,
        "name": "Alan Mathison Turing",
        "urlImage": "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Alan_Turing_Aged_16.jpg/440px-Alan_Turing_Aged_16.jpg",
        "birthDate": "1912-06-23",
        "deathDate": "1954-06-07",
        "bornPlace": "Maida Vale, Reino Unido de Gran Bretaña e Irlanda",
        "urlMap": "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d75132.22175705049!2d-0.2604624272851899!3d51.53606625037453!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48761009a098e00b%3A0x261185c6bcdb02a2!2sMaida+Vale%2C+Londres%2C+Reino+Unido!5e0!3m2!1ses!2ses!4v1549226048515",
        "imgId": 2
    }

	```
  
* ##### Error response:

	**Code**: 404 NOT FOUND
		
#### GET one author

* ##### URL:

	< /author/{id} >

* ##### Method:

	`GET`
	
* ##### Success Response:
```
 "id": 106,
    "name": "Brandon Sanderson",
    "urlImage": "https://www.goodreads.com/photo/author/38550.Brandon_Sanderson",
    "birthDate": "1975-12-19",
    "deathDate": "",
    "bornPlace": "Lincoln, Nebraska",
    "urlMap": "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d96647.87182448553!2d-96.76076790212979!3d40.80058782506782!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8796be59ca561265%3A0x633a859b1fd5deb9!2sLincoln%2C+Nebraska%2C+EE.+UU.!5e0!3m2!1ses!2ses!4v1551055373899",
    "imgId": 0,
    "books": [
        {
            "id": 77,
            "title": "Palabras Radiantes",
            "publishDate": "",
            "nameEdit": "",
            "urlEdit": "",
            "urlImgCoverPage": "",
            "urlImgEdit": "",
            "imgId": 3,
            "theme": {
                "id": 76,
                "name": "Amor"
            },
            "citation": [
                {
                    "id": 78,
                    "text": "El misterio de la vida no es un problema a resolver, sino una realidad a experimentar",
                    "textAux": null
               }
```
  
* ##### Error response:

	**Code**: 404 NOT FOUND
	
### GET all quotes

* ##### URL:

	< /citation >

* ##### Method:

	`GET`
	
* ##### Success Response:
 	 ```
	 {
        "id": 93,
        "text": "La Ilusión despierta el empeño y solamente la paciencia lo termina.",
        "textAux": null
    },
    {
        "id": 96,
        "text": "Nunca la persona llega a tal grado de perfección como cuando llena un impreso de solicitud de trabajo.",
        "textAux": null
    },
    {
        "id": 98,
        "text": "El que es bueno en la familia es también un buen ciudadano.",
        "textAux": null
    },
    {
        "id": 99,
        "text": "Haríamos muchas más cosas si creyéramos que son muchas menos las imposibles.",
        "textAux": null
    },
    {
        "id": 100,
        "text": "La sabiduría suprema es tener sueños bastante grandes para no perderlos de vista mientras se persiguen.",
        "textAux": null
    },
	```
  
* ##### Error response:

	**Code**: 404 NOT FOUND
	
	
### GET one quote

* ##### URL:

	< /citation/{id} >

* ##### Method:

	`GET`
	
* ##### Success Response:
	```
	"id": 78,
    "text": "El misterio de la vida no es un problema a resolver, sino una realidad a experimentar",
    "textAux": null,
    "book": {
        "id": 77,
        "title": "Palabras Radiantes",
        "publishDate": "",
        "nameEdit": "",
        "urlEdit": "",
        "urlImgCoverPage": "",
        "urlImgEdit": "",
        "imgId": 3,
        "author": {
            "id": 106,
            "name": "Brandon Sanderson",
            "urlImage": "https://www.goodreads.com/photo/author/38550.Brandon_Sanderson",
            "birthDate": "1975-12-19",
            "deathDate": "",
            "bornPlace": "Lincoln, Nebraska",
            "urlMap": "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d96647.87182448553!2d-96.76076790212979!3d40.80058782506782!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8796be59ca561265%3A0x633a859b1fd5deb9!2sLincoln%2C+Nebraska%2C+EE.+UU.!5e0!3m2!1ses!2ses!4v1551055373899",
            "imgId": 0
        }
    },
    "theme": {
        "id": 76,
        "name": "Amor"
    }
	```
  
* ##### Error response:

	**Code**: 404 NOT FOUND
		
