import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TabsService } from './tabs.service';
import { Tab } from './tab';

@Component({
  selector: 'tabs',
  templateUrl: 'tabs.component.html',
  styleUrls: ['tabs.component.css'],
})
export class TabsComponent implements OnInit {
  private nTabs: Tab[] = [];

  constructor(
    private tabsService:TabsService
    ){}

  ngOnInit() {
    this.exampleTabs();
  }

  exampleTabs(){
    return this.nTabs = this.tabsService.exampleTabs();;
  } 

  
}

