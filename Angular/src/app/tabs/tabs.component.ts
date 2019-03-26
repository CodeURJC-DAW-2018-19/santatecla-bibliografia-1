import {Component, OnInit} from '@angular/core';
import { Tab } from "./tab";

@Component({
  selector: 'tabs',
  templateUrl: 'tabs.component.html',
  styleUrls: ['tabs.component.css'],
})
export class TabsComponent implements OnInit {
  private nTabs: Tab[] = [];
  

  addTabs(){
    this.nTabs.push(new Tab("Tab1", "fdsfs"));
    this.nTabs.push(new Tab("Tab2", "fdsfs"));
    this.nTabs.push(new Tab("Tab3", "fdsfs"));
    this.nTabs.push(new Tab("Tab4", "fdsfs"));
  }

  ngOnInit() {
    this.addTabs();
  }
}

