import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Theme, ThemeService } from './theme.service';
import { LoginService } from '../login/login.service';

@Component({
  templateUrl: './themeDetail.component.html',
})
export class ThemeDetailComponent implements OnInit{

    theme: Theme;

    constructor(
        private router: Router, 
        public activatedRoute: ActivatedRoute, 
        public service: ThemeService,
        public loginService: LoginService) {}

    ngOnInit() {
        const id = this.activatedRoute.snapshot.params['id'];
        this.service.getTheme(id).subscribe(
            theme => this.theme = theme,
            error => console.error(error)
        );
        console.log("Current location:", this.router.url);
    }
}
