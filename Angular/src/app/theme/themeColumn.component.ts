import { Component, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MatIconRegistry, MatDialog } from '@angular/material';
import { TdMediaService, TdDigitsPipe, TdLayoutManageListComponent, tdRotateAnimation } from '@covalent/core';
import {ActivatedRoute,Router} from '@angular/router'
import { Theme, ThemeService } from './theme.service';
import { LoginService } from '../login/login.service';

@Component({
    selector: 'themeColumn',
    templateUrl: './themeColumn.component.html',
    styleUrls: ['../app.component.css'],
    animations: [tdRotateAnimation],
})
export class ThemeColumnComponent implements OnInit{
   
    themes: Theme[];

    page: number;
    constructor(
        private router: Router,
        activatedRoute: ActivatedRoute,
        private service: ThemeService,
        public loginService: LoginService
    ){
        this.page=0;
    }

    ngOnInit() {
        var createUrl: string;
        createUrl = "?page=" + (this.page);
        console.log(createUrl);
        this.service.getThemes(createUrl).subscribe(
           themes => this.themes = themes,
           error => console.log(error)
        );
    }

    loadMore(){
        var createUrl: string;
        this.page +=1;
        createUrl = "?page=" + (this.page);
        this.service.getThemes(createUrl).subscribe(
            themes => this.themes = this.themes.concat(themes),
            error => console.log(error)
         );
         console.log(this.themes);
    }
    
    newTheme() {
        this.router.navigate(['/theme']);
    }




}