import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Theme, ThemeService } from './theme.service';
import { LoginService } from '../login/login.service';
import { TabsService } from "../tabs/tabs.service";

@Component({
    templateUrl: './themeDetail.component.html',
})
export class ThemeDetailComponent implements OnInit {

    theme: Theme;

    constructor(
        private router: Router,
        public activatedRoute: ActivatedRoute,
        public service: ThemeService,
        public loginService: LoginService,
        private tabsService: TabsService) { }

    ngOnInit() {
        this.activatedRoute.params.subscribe(
            params => {
                const id = params['id'];
                this.service.getTheme(id).subscribe(
                    theme => {
                        this.theme = theme;
                        this.tabsService.addTab(this.theme.name, this.router.url);
                    },
                    error => console.error(error)
                );
            }
        )
    }
}
