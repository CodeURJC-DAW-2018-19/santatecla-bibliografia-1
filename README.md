# Santatecla-Bibliografía

This is a project based learning made by students at the URJC, the  objective of the project is making a website to manage authors, books and quotes in a bibliography supported in a data base.

## Built With

* [Bootstrap CreativeTim](https://demos.creative-tim.com/material-dashboard/docs/2.1/components/breadcrumb.html) - Used template
* [Spring](https://spring.io/tools) - Project Management
* [Maven](https://maven.apache.org/) - Dependency Management
* [MySql](https://www.mysql.com/products/workbench/) - Backend technology
* [JAVA 8](https://www.java.com/es/) - Backend programing lenguage
* [Docker](https://www.hub.docker.com/) - Create image of the application

# Phase 2 DOC

## Setting up

1. The first step, is cloning this repository in your Documents/GitHub folder.
2. The next step is to import this project in SpringSTS4 as an existing maven project. ![Image of Spring](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/assets/img/icons/Spring1.PNG)
3. Install MySQL server, remind the password that you use in the installation(we will need).
4. Intall MySQLWorkbench and create a schema named "test".
5. Edit the application.properties file to set the same pasword that you use to install MySQL Server. ![Image of MySql1](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/assets/img/icons/MySql1.PNG) 
![Image of MySql2](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/assets/img/icons/MySql2.PNG)
6. Run the project as Spring Application and then you can visit https://localhost/8443/index in your favourite browser and see the website.

## Navigation Scheme
![Image of scheme](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/assets/img/icons/Scheme.png)

You can go from every page to the rest, using the index as a main page and using a navbar to make easier to navigate.

## Page Images
![Image of index](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/assets/img/icons/Index.png)
Here you can see an example of the index/main page.

![Image of author](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/assets/img/icons/Autores.png)
Here you can see an example of the author form page.

![Image of book](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/assets/img/icons/Obras.png)
Here you can see an example of the book form page.

![Image of theme](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/assets/img/icons/Temas.png)
Here you can see an example of the theme.

## BBDD relation diagrams

![Image of UML1](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/assets/img/icons/UML1.png)

This diagram show the relations betwen entities in the DB and their cardinalities. We change the original diagram, because it was wrong (the cardinalities) in the relation between the book and the author. So we have a schema in wich one author has many books, but a book only has an author.

## Class diagram

![Image of Diagram1](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/assets/img/icons/ClassDiagram.jpg)

In this big diagram are showed the realtions in every class. We can se a pattern, wich is that all the controller are in the lower part of the diagram, and they contribute with methods (services) to the application by implementing the way that the objects are created, updated and  destroyed. In de middle section we see the reopsitories classes wich make the querys to the data base using JPA standar. And on the top of the diagram, there are the entities that model the tables that are stored on the data base (Depending on the relationship between these entites could more tables in the DDBB - relational tables).

![Image of Spring](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/assets/img/icons/CapturaClasesSpringBuena.png)

In this image is explained the folder structure of the Maven project.

![Image of book](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/assets/img/icons/DiagramaLibros.png)

This diagram shows an example of the MVC implemented in the book class. All the packages in this project have the same structure, there are an entity which models the data base table, also we have a bookRepository that simplify so much the queries to the DDBB. Also we have a controller wich defines the logic of the entity. And the user that use the controller.


## Security

The security has been implemented using Spring Security, all the configuration relative to the security is in the package user, in the SercutiryConfiguration.java . In this file we can define the public and privates URL's. Also you can define the default login and logout page. In adittion to this, we also configure some paths that the application have to ignore, so we can use css, js, and img.

# Phase 3 DOC

## Navigation Scheme

![Image of diagram](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/assets/img/icons/DiagramaFase3.jpg)

Loged as an admin you can add, delete, and update resources. And all users can view all the detailed resources following the navigation showed up.

## REST CONTROLLER DIAGRAM
 
 ![Image of diagram](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/assets/img/icons/RestDiagram.png)

 
 In this diagram is showed the relation between the RestController Service and Class, as an example with the author entity.



## API DOCUMENTATION

To know how to use the API rest go to the link below.

[API DOC](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/API.md)

# Phase 4 DOC

## Getting started with Angular
1.


## Angular class diagram

## Getting started with docker

1. Be sure u are using Windows Pro/Student/Enterprise, Linux, or iOS otherwise docker will not work in your computer.
2. Download and install Docker. [Docker download] (https://hub.docker.com/editions/community/docker-ce-desktop-windows)
3. Download and install Maven. [Maven download] (https://maven.apache.org/download.cgi)
4. Go to the /Docker folder in the project and run docker_develop.sh

## Instructions to execute the dockerized application

To run the dockerize application, you need instal docker (with docker compose)
1. From the root of the project, go to the Docker folder.
2. Generate the image of the application, by running the script "docker_develop.sh".
3. Then check if the image has been correctly created executing ``` docker images ´´´
4. Execute the instruction ```docker-compose up ´´´, that command will download the image of the DDBB and it will run the 2 containers

* Optionally if you want upload your image to a docker repository, you can use the "upload_docker_image.sh" wich need as arguments the name of the image, the version, the user of dockerhub, the password, and the repository. 
*IN THIS CASE WE UNDERSTAND YOU ARE A DEVELOP, SO YOU HAVE TO HAD MAVEN (2.*) INSTALLED*


## Authors

* **Rubén Bernabe Menéndez** - *r.bernabem@alumnos.urjc.es* - [GitHub](https://github.com/RubenBernabe)
* **Rodrigo Lopez Sanchez** - *r.lopezsanch@alumnos.urjc.es* - [GitHub](https://github.com/RodriLs)
* **Raúl Martín Pintos** - *r.martinpin@alumnos.urjc.es* - [GitHub](https://github.com/martinpin)
* **Natalia Madrueño Sierro** - *n.madrueno@alumnos.urjc.es* - [GitHub](https://github.com/madrueno)
* **Óscar Carazo Cámara** - *o.carazoc@alumnos.urjc.es* - [GitHub](https://github.com/OscarFlay96)

## Requirements Document
[Document](https://docs.google.com/document/d/1CWRq8RUXchpzgKLwoRW4MFCGlrEd05Fe1oJTlBbKZs0/edit?usp=sharing)

