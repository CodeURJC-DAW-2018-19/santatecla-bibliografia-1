FROM node:10.15.3 AS builder
#VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
COPY . ./application
WORKDIR /application

RUN npm install @angular/cli
RUN npm rebuild node-sass
RUN pwd
RUN $(npm bin)/ng build --base-href /new/ --prod

RUN ls -l /application/dist

#COPY Angular/* /usr/src/Angular/
#RUN /bin/bash -c 'ls /usr/src/Angular'
#RUN /bin/bash -c 'npm install @angular/cli'
#RUN /bin/bash -c 'cd /usr/src/Angular'
#RUN /bin/bash -c 'npm build --base-href /new/ --prod'

