# SantaTecla-Bibliografia-1 - API REST Documentation

## About our API

## How to use our API
1. Download [Postman](https://www.getpostman.com/).

## API requests

### Resources
The resource API has GET, POST, PATCH and DELETE methods.
http: // localhost:8443 followed by the containt request URL.

**All API queries have been preceded by /api**

## Authentication

#### login
Allows a user to log in.

* ##### URL:

	< /login >

* ##### Method:

	`GET`
	
* ##### Success Response:
  
  
* ##### Error response:

	**Code**: 401 UNAUTHORIZED
	
#### loguot  
Allows a user to log out.

* ##### URL:

	< /logout >

* ##### Method:

	`GET`

* ##### Success Response:

	```
	true
	```

  * ##### Error Response:

	**Code**: 404 NOT FOUND

## Admin
The following queries will be preceded by /Admin. 
  
### Obtain user data
Resource to show all users with their data.

* ##### URL


* ##### Method:

	`GET`
	
* ##### URL Params:
	* page=[int]
	* size=[int]
	
* ##### Example of query:

	* URL
		
		`/api/Admin/UserData/?page=1&size=10`
  
* ##### Success Response:
```	

```
  
* ##### Error Response:

	**Code**: 401 UNAUTHORIZED
  


### Resource to show one unit

* ##### URL:

	< /{id} >

* ##### Method:
	
	`GET`
	
* ##### URL Params:

	`id=[long]`
	
* ##### Example of query:

	* URL
		
		`/api/Unit/1`
		
* ##### Success response:

	```
	{
	    "id": 1,
	    "name": "Unidad 1"
	}
	```
	
* ##### Error response:

	**Code:** 404 NOT FOUND



### Upload images of the unit created

* ##### URL:

	< / {id}/Images>

* ##### Method:
	
	`POST`

* ##### URL Params:

	`id=[long]`
	
* ##### URL Params:

	`Empty`
	
* ##### Example of query:

	* URL
		
		`/api/Unit/1/Images`

* ##### Data Params:

	```
	images[]: image file format (*.jpg, *.jpeg, *.png) (length = 9)
	```
* ##### Error response:

	**Code:** 500 INTERNAL SERVER ERROR or 400 BAD REQUEST

### Change the name of the unit

* ##### URL:

	< /{id} >

* ##### Method:
	
	`PUT`
	
* ##### URL Params:

	`id=[long]`
	
* ##### Example of query:

	* URL
		
		`/api/Unit/1`
		
* ##### Data Params:
	
	```
	{
		"name": "Unidad Prueba"
	}
	```

* ##### Success response:

	```
	{
	    "id": 1,
	    "name": "Unidad Prueba"
	}
	```
	
* ##### Error response:

	**Code:** 404 NOT FOUND

### Delete unit

* ##### URL:

	< /{id} >

* ##### Method:
	
	`DELETE`
	
* ##### URL Params:

	`id=[long]`
	
* ##### Example of query:

	* URL
		
		`/api/Unit/1`
		
* ##### Data Params:
	
	```
	{
		"name": "Unidad Prueba"
	}
	```

* ##### Success response:

	```
	{
	    "id": 1,
	    "name": "Unidad Prueba"
	}
	```
	
* ##### Error response:

	**Code:** 404 NOT FOUND


## Lesson
The following queries will be preceded by /Unit.

### Resource to show all lessons

* ##### URL:

	< / / >

* ##### Method:
	
	`GET`
	
* ##### URL Params:

	`Empty`
	
* ##### Example of query:

	* URL
		
		`/api/`
		
* ##### Success response:

	```
	
	
	```
