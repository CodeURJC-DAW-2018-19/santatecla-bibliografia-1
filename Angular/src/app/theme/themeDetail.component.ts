import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Theme, ThemeService } from './theme.service';
import { LoginService } from '../login/login.service';
import { TabsService } from "../tabs/tabs.service";

@Component({
    templateUrl: './themeDetail.component.html',
    selector: 'themeDetail',
})
export class ThemeDetailComponent {
    @Input()
    theme: Theme;

    constructor(
        private router: Router,
        public activatedRoute: ActivatedRoute,
        public service: ThemeService,
        public loginService: LoginService,
        private tabsService: TabsService) { }


}
