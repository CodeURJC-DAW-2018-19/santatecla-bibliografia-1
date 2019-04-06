FROM node:10.15.3 AS builder

COPY . ./application
WORKDIR /application

RUN npm install @angular/cli
RUN npm rebuild node-sass
RUN $(npm bin)/ng build --base-href /new/ --prod
#RUN ls -l /application/dist/my-app

