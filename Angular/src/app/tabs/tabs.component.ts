import {Component, OnInit} from '@angular/core';
import { Tab } from "./tab";

@Component({
  selector: 'tabs',
  templateUrl: 'tabs.component.html',
  styleUrls: ['tabs.component.css'],
})
export class TabsComponent implements OnInit {
  private nTabs: Tab[] = [];
  

  exampleTabs(){
    this.nTabs.push(new Tab("Tab1", "fdsfs"));
    this.nTabs.push(new Tab("Tab2", "fdsfs"));
    this.nTabs.push(new Tab("Tab3", "fdsfs"));
    this.nTabs.push(new Tab("Tab4", "fdsfs"));
  }

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

