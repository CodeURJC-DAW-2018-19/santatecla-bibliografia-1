# Santatecla-Bibliografía

This is a project based learning made by students at the URJC, the  objective of the project is making a website to manage authors, books and quotes in a bibliography supported in a data base.

## Built With
* [Bootstrap CreativeTim](https://demos.creative-tim.com/material-dashboard/docs/2.1/components/breadcrumb.html) - Used template
* [Spring](https://spring.io/tools) - Project Management
* [Maven](https://maven.apache.org/) - Dependency Management
* [MySql](https://www.mysql.com/products/workbench/) - Backend technology
* [JAVA 8] (https://www.java.com/es/) - Backend programmign lenguage

## Setting up

1. The first step, is cloning this repository in your Documents/GitHub folder.
2. The next step is to import this project in SpringSTS4 as an existing maven project. ![Image of Spring](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/assets/img/icons/Spring1.PNG)
3. Install MySQL server, remind the password that you use in the installation(we will need).
4. Intall MySQLWorkbench and create a schema named "test".
5. Edit the application.properties file to set the same pasword that you use to install MySQL Server. ![Image of MySql1](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/assets/img/icons/MySql1.PNG) 
![Image of MySql2](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/assets/img/icons/MySql2.PNG)
6. Run the project as Spring Application and then you can visit https://localhost/8443/index in your favourite browser and see the website.

# Phase 2 DOC
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

Login as an admin you can add, delete, and update resources. And all users can view all the detailed resources following the navigation showed up.

## API DOCUMENTATION
[API DOC](https://github.com/CodeURJC-DAW-2018-19/santatecla-bibliografia-1/blob/master/API.md)

## Getting started with docker
1. Be sure u are using Windows Pro/Student/Enterprise, otherwise docker will not work in your computer.
2. Download and install Docker. [Docker download] (https://hub.docker.com/editions/community/docker-ce-desktop-windows)
3. Download and install Maven. [Maven download] (https://maven.apache.org/download.cgi)
4. Go to the /Docker folder in the project and run docker_develop.sh
5. Now go to yor prefered terminalñd to the same folder and write in the command line ``` docker-compose up ```

## Instructions to execute the dockerized application
1.
2.
3.
4.
5.
6.


## Authors

* **Rubén Bernabe Menéndez** - *r.bernabem@alumnos.urjc.es* - [GitHub](https://github.com/RubenBernabe)
* **Rodrigo Lopez Sanchez** - *r.lopezsanch@alumnos.urjc.es* - [GitHub](https://github.com/RodriLs)
* **Raúl Martín Pintos** - *r.martinpin@alumnos.urjc.es* - [GitHub](https://github.com/martinpin)
* **Natalia Madrueño Sierro** - *n.madrueno@alumnos.urjc.es* - [GitHub](https://github.com/madrueno)
* **Óscar Carazo Cámara** - *o.carazoc@alumnos.urjc.es* - [GitHub](https://github.com/OscarFlay96)

## Requirements Document
[Document](https://docs.google.com/document/d/1CWRq8RUXchpzgKLwoRW4MFCGlrEd05Fe1oJTlBbKZs0/edit?usp=sharing)

Doc: https://docs.google.com/document/d/1nyK1UKLXcJqGNJcQNH4hE4urLx0fvnFmNnkJ2F11rcE/edit?usp=sharing
