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

The basic authentication implemented can be tested in postman using the Autorization form, showed in the image below. First you need to send one BAsic Auth petition, and then inherith auth from parent to use it during the session.

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
`
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

	**Code**: 401 UNAUTHORIZED or 400 BAD REQUEST

### Sign up

* ##### URL:

	< /singup >

* ##### Method:

	`GET`
	
* ##### Body data:
  ```
	{
	"name": "Óscar",
	"passwordHash": "qwerty"
	}
  ```
 * ##### Success Response:

  	```
		{
	    "id": 120,
	    "name": "Óscar",
	    "roles": [
		"ROLE_USER"
	    ]
	}
	```
	
* ##### Error response:

	**Code**: 405 Method not allowed
	
	
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
	
	
#### GET paged books

* ##### URL:

	< /books?page={numPage} >

* ##### Method:

	`GET`
	
* ##### Success Response:
  	```
	  {
        "id": 32,
        "title": "10",
        "publishDate": "",
        "nameEdit": "",
        "urlEdit": "",
        "urlImgCoverPage": "",
        "urlImgEdit": "",
        "imgId": -2
    },
    {
        "id": 35,
        "title": "11",
        "publishDate": "",
        "nameEdit": "",
        "urlEdit": "",
        "urlImgCoverPage": "",
        "urlImgEdit": "",
        "imgId": -2
    },
    {
        "id": 38,
        "title": "12",
        "publishDate": "",
        "nameEdit": "",
        "urlEdit": "",
        "urlImgCoverPage": "",
        "urlImgEdit": "",
        "imgId": -2
    },
    {
        "id": 41,
        "title": "13",
        "publishDate": "",
        "nameEdit": "",
        "urlEdit": "",
        "urlImgCoverPage": "",
        "urlImgEdit": "",
        "imgId": -2
    },
    {
        "id": 44,
        "title": "14",
        "publishDate": "",
        "nameEdit": "",
        "urlEdit": "",
        "urlImgCoverPage": "",
        "urlImgEdit": "",
        "imgId": -2
    },
    {
        "id": 47,
        "title": "15",
        "publishDate": "",
        "nameEdit": "",
        "urlEdit": "",
        "urlImgCoverPage": "",
        "urlImgEdit": "",
        "imgId": -2
    },
    {
        "id": 50,
        "title": "16",
        "publishDate": "",
        "nameEdit": "",
        "urlEdit": "",
        "urlImgCoverPage": "",
        "urlImgEdit": "",
        "imgId": -2
    },
    {
        "id": 53,
        "title": "17",
        "publishDate": "",
        "nameEdit": "",
        "urlEdit": "",
        "urlImgCoverPage": "",
        "urlImgEdit": "",
        "imgId": -2
    },
    {
        "id": 56,
        "title": "18",
        "publishDate": "",
        "nameEdit": "",
        "urlEdit": "",
        "urlImgCoverPage": "",
        "urlImgEdit": "",
        "imgId": -2
    },
    {
        "id": 59,
        "title": "19",
        "publishDate": "",
        "nameEdit": "",
        "urlEdit": "",
        "urlImgCoverPage": "",
        "urlImgEdit": "",
        "imgId": -2
    }
	```
  
* ##### Error response:

	**Code**: 404 NOT FOUND
	
#### GET paged books titles

* ##### URL:

	< /books?page={numPage}&filter=titles >

* ##### Method:

	`GET`
	
* ##### Success Response
```
	[
    "Palabras Radiantes",
    "Romancero Gitano",
    "Nacidos de la bruma",
    "El año de los delfines",
    "Refranero",
    "Harry Potter y la Piedra Filosofal",
    "IT",
    "El Resplandor",
    "zzz0",
    "zzz1"
     ]
```

* ##### Error response:

	**Code**: 400 BAD REQUEST
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
	```
  
* ##### Error response:

	**Code**: 404 NOT FOUND
	
	
#### GET book by name

* ##### URL:

	< /book-search/{bookName} >

* ##### Method:

	`GET`
	
* ##### Success Response:
  	```
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
            },
            {
                "id": 79,
                "text": "p1",
                "textAux": null
            },
            {
                "id": 80,
                "text": "p2",
                "textAux": null
            },
            {
                "id": 81,
                "text": "p3",
                "textAux": null
            },
            {
                "id": 82,
                "text": "p4",
                "textAux": null
            },
            {
                "id": 83,
                "text": "p5",
                "textAux": null
            },
            {
                "id": 84,
                "text": "p6",
                "textAux": null
            },
            {
                "id": 86,
                "text": "p7",
                "textAux": null
            },
            {
                "id": 87,
                "text": "p9",
                "textAux": null
            },
            {
                "id": 88,
                "text": "p10",
                "textAux": null
            },
            {
                "id": 89,
                "text": "p11",
                "textAux": null
            },
            {
                "id": 90,
                "text": "p12",
                "textAux": null
            }
        ],
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
	
	
#### GET paged themes

* ##### URL:

	< /theme-pageable?page={numPage} >

* ##### Method:

	`GET`
	
* ##### Success Response:
  	```
   {
        "id": 3,
        "name": "0"
    },
    {
        "id": 6,
        "name": "1"
    },
    {
        "id": 9,
        "name": "2"
    },
    {
        "id": 12,
        "name": "3"
    },
    {
        "id": 15,
        "name": "4"
    },
    {
        "id": 18,
        "name": "5"
    },
    {
        "id": 21,
        "name": "6"
    },
    {
        "id": 24,
        "name": "7"
    },
    {
        "id": 27,
        "name": "8"
    },
    {
        "id": 30,
        "name": "9"
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
	
	
#### GET theme by name

* ##### URL:

	< /theme-search/{themeName} >

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
                    {
                        "id": 79,
                        "text": "p1",
                        "textAux": null
                    },
                    {
                        "id": 80,
                        "text": "p2",
                        "textAux": null
                    },
                    {
                        "id": 81,
                        "text": "p3",
                        "textAux": null
                    },
                    {
                        "id": 82,
                        "text": "p4",
                        "textAux": null
                    },
                    {
                        "id": 83,
                        "text": "p5",
                        "textAux": null
                    },
                    {
                        "id": 84,
                        "text": "p6",
                        "textAux": null
                    },
                    {
                        "id": 86,
                        "text": "p7",
                        "textAux": null
                    },
                    {
                        "id": 87,
                        "text": "p9",
                        "textAux": null
                    },
                    {
                        "id": 88,
                        "text": "p10",
                        "textAux": null
                    },
                    {
                        "id": 89,
                        "text": "p11",
                        "textAux": null
                    },
                    {
                        "id": 90,
                        "text": "p12",
                        "textAux": null
                    }
                ],
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
            }
        ]
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


#### GET paged authors

* ##### URL:

	< /author-pageable?page={numPage} >

* ##### Method:

	`GET`
	
* ##### Success Response:
  	```
    {
        "id": 1,
        "name": "0",
        "urlImage": "0",
        "birthDate": "0",
        "deathDate": "0",
        "bornPlace": "0",
        "urlMap": "0",
        "imgId": -1
    },
    {
        "id": 4,
        "name": "1",
        "urlImage": "1",
        "birthDate": "1",
        "deathDate": "1",
        "bornPlace": "1",
        "urlMap": "1",
        "imgId": -1
    },
    {
        "id": 7,
        "name": "2",
        "urlImage": "2",
        "birthDate": "2",
        "deathDate": "2",
        "bornPlace": "2",
        "urlMap": "2",
        "imgId": -1
    },
    {
        "id": 10,
        "name": "3",
        "urlImage": "3",
        "birthDate": "3",
        "deathDate": "3",
        "bornPlace": "3",
        "urlMap": "3",
        "imgId": -1
    },
    {
        "id": 13,
        "name": "4",
        "urlImage": "4",
        "birthDate": "4",
        "deathDate": "4",
        "bornPlace": "4",
        "urlMap": "4",
        "imgId": -1
    },
    {
        "id": 16,
        "name": "5",
        "urlImage": "5",
        "birthDate": "5",
        "deathDate": "5",
        "bornPlace": "5",
        "urlMap": "5",
        "imgId": -1
    },
    {
        "id": 19,
        "name": "6",
        "urlImage": "6",
        "birthDate": "6",
        "deathDate": "6",
        "bornPlace": "6",
        "urlMap": "6",
        "imgId": -1
    },
    {
        "id": 22,
        "name": "7",
        "urlImage": "7",
        "birthDate": "7",
        "deathDate": "7",
        "bornPlace": "7",
        "urlMap": "7",
        "imgId": -1
    },
    {
        "id": 25,
        "name": "8",
        "urlImage": "8",
        "birthDate": "8",
        "deathDate": "8",
        "bornPlace": "8",
        "urlMap": "8",
        "imgId": -1
    },
    {
        "id": 28,
        "name": "9",
        "urlImage": "9",
        "birthDate": "9",
        "deathDate": "9",
        "bornPlace": "9",
        "urlMap": "9",
        "imgId": -1
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


#### GET author by name

* ##### URL:

	< /author-search/{authorName} >

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
                    },
                    {
                        "id": 79,
                        "text": "p1",
                        "textAux": null
                    },
                    {
                        "id": 80,
                        "text": "p2",
                        "textAux": null
                    },
                    {
                        "id": 81,
                        "text": "p3",
                        "textAux": null
                    },
                    {
                        "id": 82,
                        "text": "p4",
                        "textAux": null
                    },
                    {
                        "id": 83,
                        "text": "p5",
                        "textAux": null
                    },
                    {
                        "id": 84,
                        "text": "p6",
                        "textAux": null
                    },
                    {
                        "id": 86,
                        "text": "p7",
                        "textAux": null
                    },
                    {
                        "id": 87,
                        "text": "p9",
                        "textAux": null
                    },
                    {
                        "id": 88,
                        "text": "p10",
                        "textAux": null
                    },
                    {
                        "id": 89,
                        "text": "p11",
                        "textAux": null
                    },
                    {
                        "id": 90,
                        "text": "p12",
                        "textAux": null
                    }
                ]
            }
        ]
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
	
	
#### GET paged quotes

* ##### URL:

	< /citation-pageable?page={numPage} >

* ##### Method:

	`GET`
	
* ##### Success Response:
  	```
    {
        "id": 78,
        "text": "El misterio de la vida no es un problema a resolver, sino una realidad a experimentar",
        "textAux": null
    },
    {
        "id": 79,
        "text": "p1",
        "textAux": null
    },
    {
        "id": 80,
        "text": "p2",
        "textAux": null
    },
    {
        "id": 81,
        "text": "p3",
        "textAux": null
    },
    {
        "id": 82,
        "text": "p4",
        "textAux": null
    },
    {
        "id": 83,
        "text": "p5",
        "textAux": null
    },
    {
        "id": 84,
        "text": "p6",
        "textAux": null
    },
    {
        "id": 85,
        "text": "p8",
        "textAux": null
    },
    {
        "id": 86,
        "text": "p7",
        "textAux": null
    },
    {
        "id": 87,
        "text": "p9",
        "textAux": null
    }
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
	
#### GET quote by name

* ##### URL:

	< /citation-search/{quoteName} >

* ##### Method:

	`GET`
	
* ##### Success Response:
  	```
	   {
        "id": 104,
        "text": "Donde hay amor no hay señor, que todo lo iguala el amor.",
        "textAux": null,
        "book": {
            "id": 97,
            "title": "Refranero",
            "publishDate": "",
            "nameEdit": "",
            "urlEdit": "",
            "urlImgCoverPage": "",
            "urlImgEdit": "",
            "imgId": -1,
            "author": {
                "id": 108,
                "name": "Alan Mathison Turing",
                "urlImage": "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Alan_Turing_Aged_16.jpg/440px-Alan_Turing_Aged_16.jpg",
                "birthDate": "1912-06-23",
                "deathDate": "1954-06-07",
                "bornPlace": "Maida Vale, Reino Unido de Gran Bretaña e Irlanda",
                "urlMap": "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d75132.22175705049!2d-0.2604624272851899!3d51.53606625037453!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48761009a098e00b%3A0x261185c6bcdb02a2!2sMaida+Vale%2C+Londres%2C+Reino+Unido!5e0!3m2!1ses!2ses!4v1549226048515",
                "imgId": 2
            }
        },
        "theme": {
            "id": 94,
            "name": "Clasico"
        }
    }
	```
  
* ##### Error response:

	**Code**: 404 NOT FOUND
	
	


### POST requests

Only the admin is allowed to do POST requests to the API, some of the post requests are allowed to upload images.

#### POST a book 

* ##### URL

	< /book>

* ##### Method:

	`POST`
  
* ##### Data Params

	```
	{
	    "title": "El titulo del libro",
	    "publishDate": "La fecha de publicacion",
	    "nameEdit": "El nombre de edicion",
	    "urlEdit": "La url de edicion",
	    "urlImgCoverPage": "La imagen de portada",
	    "urlImgEdit": "La imagen de edicion",
	    "imgId": -2
	}
	
	```
	
* ##### Success response:

	```
	{
	    "id": 113,
	    "title": "El titulo del libro",
	    "publishDate": "La fecha de publicacion",
	    "nameEdit": "El nombre de edicion",
	    "urlEdit": "La url de edicion",
	    "urlImgCoverPage": "La imagen de portada",
	    "urlImgEdit": "La imagen de edicion",
	    "imgId": -2,
	    "theme": null,
	    "citation": null,
	    "author": null,
	    "citations": null
	}
	
	```	

* ##### Error response:

	**Code**: 400 Bad request

	

#### POST a theme

* ##### URL

	< /theme>

* ##### Method:

	`POST`
  
* ##### Body data

```
		{
	    "name": "Nombre del tema",
	    "books": [
		Here you can add the data of the book is theme related
		]
		}

```
	
* ##### Success response:

```
	{
	    "id": 117,
	    "name": "Nombre del tema",
	    "books": [],
	    "book": []
	}

```

* ##### Error response:

	**Code**: 400 Bad request

* ##### Confirmation response:
	
	**Code**: 201 Created


#### POST an author 

* ##### URL

	< /author>

* ##### Method:

	`POST`
  
* ##### Body data

```
	{
	    "name": "El nombre del autor",
	    "urlImage": "La url de la imagen",
	    "birthDate": "La fecha de nacimiento",
	    "deathDate": "La fecha de muerte",
	    "bornPlace": "El lugar de nacimiento",
	    "urlMap": "La url del mapa",
	    "imgId": 2,
	    "books": [ {
		"id": 112,
		"title": "El titulo del libro",
		"publishDate": "La fecha de publicacion",
		"nameEdit": "El nombre de edicion",
		"urlEdit": "La url de edicion",
		"urlImgCoverPage": "La imagen de portada",
		"urlImgEdit": "La imagen de edicion",
		"imgId": -2
	    }]
	}
```

	
* ##### Success response:

```
	{
	    "id": 112,
	    "name": "El nombre del autor",
	    "urlImage": "La url de la imagen",
	    "birthDate": "La fecha de nacimiento",
	    "deathDate": "La fecha de muerte",
	    "bornPlace": "El lugar de nacimiento",
	    "urlMap": "La url del mapa",
	    "imgId": 2,
	    "books": [
		null
	    ],
	    "bornDate": "La fecha de nacimiento"
	}

```

* ##### Error response:

	**Code**: 400 Bad request

* ##### Confirmation response:
	
	**Code**: 201 Created

	
#### POST a quote

* ##### URL

	< /citation>

* ##### Method:

	`POST`
  
* ##### Body data

```
{
    "text": "Texto",
    "textAux": "Texto auxiliar",
    "book": {
        "id": 112,
        "title": "El titulo del libro",
        "publishDate": "La fecha de publicacion",
        "nameEdit": "El nombre de edicion",
        "urlEdit": "La url de edicion",
        "urlImgCoverPage": "La imagen de portada",
        "urlImgEdit": "La imagen de edicion",
        "imgId": -2
    },
    "theme": {"id": 116,
    "name": "Nombre del tema"}
}

```
	
* ##### Success response:

```
	{
	    "id": 118,
	    "text": "Texto",
	    "textAux": "Texto auxiliar",
	    "book": null,
	    "theme": {
		"id": 116,
		"name": "Nombre del tema"
	    }
	}

```


* ##### Error response:

	**Code**: 400 Bad request

* ##### Confirmation response:
	
	**Code**: 201 Created
	

### DELETE requests

Only the admin is allowed to do DELETE requests to the API.

#### DELETE a book 

* ##### URL:

	< /book/{id} >

* ##### Method:

	`DELETE`
	
* ##### Success Response:
 ```
    {
    "id": 2,
    "title": "0",
    "publishDate": "",
    "nameEdit": "",
    "urlEdit": "",
    "urlImgCoverPage": "",
    "urlImgEdit": "",
    "imgId": -2,
    "theme": null,
    "citation": [],
    "author": null
	}
```
  
* ##### Error response:

	**Code**: 404 NOT FOUND or 500 INTERNAL SERVER ERROR
	
	
	
#### DELETE a theme 

* ##### URL:

	< /theme/{id} >

* ##### Method:

	`DELETE`
	
* ##### Success Response:

 ```
    {
    "id": 3,
    "name": "0",
    "books": []
    }
```
  
* ##### Error response:

	**Code**: 404 NOT FOUND or 500 INTERNAL SERVER ERROR


#### DELETE an author 

* ##### URL:

	< /author/{id} >

* ##### Method:

	`DELETE`
	
* ##### Success Response:
	 ```
	 {
	    "id": 1,
	    "name": "0",
	    "urlImage": "0",
	    "birthDate": "0",
	    "deathDate": "0",
	    "bornPlace": "0",
	    "urlMap": "0",
	    "imgId": -1,
	    "books": []
	}
  	
	```
  
* ##### Error response:

	**Code**: 404 NOT FOUND or 500 INTERNAL SERVER ERROR
	
#### DELETE a quote 

* ##### URL:

	< /citation/{id} >

* ##### Method:

	`DELETE`
	
* ##### Success Response:

 ```
 {
    "id": 85,
    "text": "p8",
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
}

```
  
* ##### Error response:

	**Code**: 404 NOT FOUND or 500 INTERNAL SERVER ERROR
		
### PATCH requests	

PATCH method is only available for admin, and updates a rosurce in the API rest. The data is sended in the body as a JSON.
		
#### PATCH a book

* ##### URL

	< /book/{id}>

* ##### Method:

	`PATCH`
  
* ##### Body data

```
	{
        "id": 2,
        "title": "Libro editado",
        "publishDate": "",
        "nameEdit": "",
        "urlEdit": "",
        "urlImgCoverPage": "",
        "urlImgEdit": "",
        "imgId": -2
    	}
    
```
	
* ##### Success response:

```
    {
    "id": 2,
    "title": "Libro editado",
    "publishDate": "",
    "nameEdit": "",
    "urlEdit": "",
    "urlImgCoverPage": "",
    "urlImgEdit": "",
    "imgId": -2,
    "theme": null,
    "citation": [],
    "author": null
     }
```

* ##### Error response:

	**Code**: 404 NOT FOUND

* ##### Confirmation response:
	
	**Code**: 200 OK

#### PATCH a theme

* ##### URL

	< /theme/{id}>

* ##### Method:

	`PATCH`
  
* ##### Body data

```
    {
        "id": 3,
        "name": "Nuevo tema editado"
    }
    
```
	
* ##### Success response:

```
  {
    "id": 3,
    "name": "Nuevo tema editado",
    "books": null
   }
 
```

* ##### Error response:

	**Code**: 404 NOT FOUND

* ##### Confirmation response:
	
	**Code**: 200 OK

#### PATCH an author
* ##### URL

	< /author/{id}>

* ##### Method:

	`PATCH`
  
* ##### Body data

```
   {
        "id": 1,
        "name": "Nombre editado",
        "urlImage": "0",
        "birthDate": "0",
        "deathDate": "0",
        "bornPlace": "Editado",
        "urlMap": "0",
        "imgId": -1
    }
    
```
	
* ##### Success response:

```
	{
	    "id": 1,
	    "name": "Nombre editado",
	    "urlImage": "0",
	    "birthDate": "0",
	    "deathDate": "0",
	    "bornPlace": "Editado",
	    "urlMap": "0",
	    "imgId": -1,
	    "books": []
	}

```

* ##### Error response:

	**Code**: 404 NOT FOUND

* ##### Confirmation response:
	
	**Code**: 200 OK

#### PATCH a quote

* ##### URL

	< /citation/{id}>

* ##### Method:

	`PATCH`
  
* ##### Body data

```
 {
        "id": 79,
        "text": "Solo sé que no se nada",
        "textAux": "Cita editada"
    }
    
```
	
* ##### Success response:

```
  {
        "id": 79,
        "text": "Solo sé que no se nada",
        "textAux": "Cita editada"
    }
```

* ##### Error response:

	**Code**: 404 NOT FOUND

* ##### Confirmation response:
	
	**Code**: 200 OK

