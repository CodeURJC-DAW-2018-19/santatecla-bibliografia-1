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
    private tabsService: TabsService,
    private loginService: LoginService
  ) { }

  ngOnInit() {
    if (this.loginService.isLogged){
      let tabs = this.tabsService.getLocalstorage();
      if (tabs != null){
        this.nTabs=tabs;
      } else{
        this.nTabs = this.tabsService.initTabs();
      }
      
    }
    
  }

  closeTab(tab:Tab){
    console.log("close tabs")
    this.nTabs = this.tabsService.removeTab(tab);
  }


}

