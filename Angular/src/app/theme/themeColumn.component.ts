import { Component, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MatIconRegistry, MatDialog } from '@angular/material';
import { TdMediaService, TdDigitsPipe, TdLayoutManageListComponent, tdRotateAnimation } from '@covalent/core';
import {ActivatedRoute,Router} from '@angular/router'
import { Theme, ThemeService } from './theme.service';

@Component({
    selector: 'themeColumn',
    templateUrl: './themeColumn.component.html',
    styleUrls: ['../app.component.css'],
    animations: [tdRotateAnimation],
})
export class ThemeColumnComponent implements OnInit{
   
    themes: Theme[];

    constructor(
        private router: Router,
        activatedRoute: ActivatedRoute,
        private service: ThemeService,
    ){}

    ngOnInit() {
        this.service.getThemes().subscribe(
           themes => this.themes = themes,
           error => console.log(error)
        );
    }
    
    newTheme() {
        this.router.navigate(['/theme']);
    }




}