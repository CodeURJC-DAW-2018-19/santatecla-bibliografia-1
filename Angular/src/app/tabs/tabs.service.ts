import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators'
import { HttpHeaders } from '@angular/common/http';
import { Tab } from './tab';
import { exists } from 'fs';
import { prepareSyntheticListenerFunctionName } from '@angular/compiler/src/render3/util';

@Injectable()
export class TabsService {
    private nTabs: Tab[] = [];

    constructor(private http: Http) { }

    removeTab(name: string) {
        for (var i = 0; i < this.nTabs.length; i++) {
            if (this.nTabs[i].Name === name) {
                this.nTabs.splice(i);
            }
        }
    }

    addTab(name: string, url: string) {
        let exist: Boolean;
        exist = false;
        for (var i = 0; i < this.nTabs.length; i++) {
            if (this.nTabs[i].Url === url) {
                exist = true;
            }
        }
        if (!exist){
            this.nTabs.push(new Tab(name, url));
            console.log("current tab", url)
        }   
        this.showTabs();
    }

    showTabs(){
        for (let tab in this.nTabs){
            console.log("tab: ",tab);
        }
    }

    exampleTabs(){
        this.nTabs.push(new Tab("Inicio", "/"));
        this.nTabs.push(new Tab("Temas", "/theme"));
        this.nTabs.push(new Tab("Autores", "/author"));
        this.nTabs.push(new Tab("Libros", "/book"));
        return this.nTabs;
      } 

    changeTab(name: string) {
        console.log("1");
        if (name === "Inicio") {
            console.log("1");
            //this.router.navigateByUrl('/');
        } else if (name === "Temas") {
            //this.router.navigateByUrl('/themes');
            console.log("1");
        } else if (name === "Autores") {
            //this.router.navigateByUrl('/authors');
            console.log("1");
        } else if (name === "Libros") {
            //this.router.navigateByUrl('/libros');
            console.log("1");
        }

    };

}
