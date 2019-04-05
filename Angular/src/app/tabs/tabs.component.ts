import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TabsService } from './tabs.service';
import { LoginService } from '../login/login.service';
import { Tab } from './tab';

@Component({
  selector: 'tabs',
  templateUrl: 'tabs.component.html',
  styleUrls: ['tabs.component.css'],
})
export class TabsComponent implements OnInit {
  private nTabs: Tab[] = [];

  constructor(
    private tabsService: TabsService
  ) { }

  ngOnInit() {
    this.tabsService.readyloadData().subscribe(
      nTabs => this.nTabs = nTabs
    );

    this.tabsService.loadSaveInfo();
  }

  closeTab(tab:Tab){
    console.log("close tabs")
    this.nTabs = this.tabsService.removeTab(tab);
  }


}

