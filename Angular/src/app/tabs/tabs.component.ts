import {Component, OnInit} from '@angular/core';


@Component({
  selector: 'tabs',
  templateUrl: 'tabs.component.html',
  styleUrls: ['tabs.component.css'],
})
export class TabsComponent implements OnInit {
  private nTabs: string[] = [];
  
  addTabs(){
    this.nTabs.push("Tab1");
    this.nTabs.push("Tab2");
    this.nTabs.push("Tab2");
    this.nTabs.push("Tab4");
  }

  ngOnInit() {
    this.addTabs();
  }
}

