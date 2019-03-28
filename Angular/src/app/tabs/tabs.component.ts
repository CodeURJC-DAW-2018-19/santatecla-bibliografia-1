import {Component, OnInit} from '@angular/core';
import { Tab } from "./tab";
import { Router } from '@angular/router';

@Component({
  selector: 'tabs',
  templateUrl: 'tabs.component.html',
  styleUrls: ['tabs.component.css'],
})
export class TabsComponent implements OnInit {
  private nTabs: Tab[] = [];
  

  exampleTabs(){
    this.nTabs.push(new Tab("Inicio", "/"));
    this.nTabs.push(new Tab("Temas", "/theme"));
    this.nTabs.push(new Tab("Autores", "/author"));
    this.nTabs.push(new Tab("Libros", "/book"));
  } 

  changeTab(name:string) {
    console.log("1");
    if (name === "Inicio"){
      console.log("1");
      //this.router.navigateByUrl('/');
    } else if (name === "Temas"){
      //this.router.navigateByUrl('/themes');
      console.log("1");
    } else if (name === "Autores"){
      //this.router.navigateByUrl('/authors');
      console.log("1");
    } else if (name === "Libros"){
      //this.router.navigateByUrl('/libros');
      console.log("1");
    } 
    
  };

  removeTab(name:string){
    for (var i=0; i<this.nTabs.length; i++){
      if (this.nTabs[i].Name === name){
        this.nTabs.splice(i);
      }
    }
  }

  addTab(name:string, url:string){
    this.nTabs.push(new Tab("name", "url"));
  }

  ngOnInit() {
    this.exampleTabs();
  }
}

