import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable, Subject } from 'rxjs';
import { map, catchError } from 'rxjs/operators'
import { HttpHeaders } from '@angular/common/http';
import { Tab } from './tab';
import { prepareSyntheticListenerFunctionName } from '@angular/compiler/src/render3/util';
import { LoginService } from '../login/login.service';

@Injectable()
export class TabsService {
    private nTabs: Tab[] = [];
    private loadData = new Subject<Tab[]>();

    constructor(private http: Http, private loginService: LoginService) { }

    loadSaveInfo() {
        if (this.loginService.isLogged) {
            let tabs = this.getLocalstorage();

            if (tabs != null) {
                this.nTabs = tabs;
            } else {
                this.nTabs = this.initTabs();
            }
        } else {
            this.nTabs = this.initTabs();
        }

        this.loadData.next(this.nTabs);
    }

    readyloadData(){
        return this.loadData.asObservable();
    }

    saveLocalstorage() {
        localStorage.setItem('nTabs', JSON.stringify(this.nTabs));
    }

    getLocalstorage() {
        return JSON.parse(localStorage.getItem('nTabs'));
    }

    removeTab(tab: Tab) {
        let index = this.nTabs.indexOf(tab);
        this.nTabs.splice(index, 1);
        this.saveLocalstorage();
        this.showTabs();
        return this.nTabs;
    }

    showTabs() {
        for (let tab in this.nTabs) {
            console.log("tab: ", tab)
        }
    }

    addTab(name: string, url: string) {
        console.log("Current array: " + JSON.stringify(this.nTabs) + " search " + name)
        let index = this.nTabs.map(t => t.name).indexOf(name);
        console.log("result " + index)
        
        if(index == -1){
            this.nTabs.push({name: name, url: url});
            console.log("current tab", url)
            this.saveLocalstorage();
        }
    }

    initTabs() {
        return [];
    }

    logoutTabs(){
        this.nTabs = [];
        this.loadData.next(this.nTabs);
    }

    exampleTabs() {
        this.nTabs.push({name: "Inicio", url: "/"});
        this.nTabs.push({name: "Temas", url: "/theme"});
        this.nTabs.push({name: "Autores", url: "/author"});
        this.nTabs.push({name: "Libros", url: "/book"});
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
