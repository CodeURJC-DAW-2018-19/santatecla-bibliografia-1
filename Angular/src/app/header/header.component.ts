import { Component, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MatIconRegistry, MatDialog } from '@angular/material';
import { TdMediaService, TdDigitsPipe, TdLayoutManageListComponent, tdRotateAnimation } from '@covalent/core';
import { DatePipe } from '@angular/common';

@Component({
    selector: 'header',
    templateUrl: './header.component.html',
    styleUrls: ['../app.component.css'],
    animations: [tdRotateAnimation],
})
export class HeaderComponent {
    
    miniNav: boolean = false;
    
    // Theme toggle
    get activeTheme(): string {
        return localStorage.getItem('theme');
    }
    theme(theme: string): void {
        localStorage.setItem('theme', theme);
    }

    constructor(
        public media: TdMediaService,
        public dialog: MatDialog,
        private _iconRegistry: MatIconRegistry,
        private _domSanitizer: DomSanitizer,
    ) {
        this._iconRegistry.addSvgIconInNamespace(
            'assets',
            'covalent',
            this._domSanitizer.bypassSecurityTrustResourceUrl(
                'https://raw.githubusercontent.com/Teradata/covalent-quickstart/develop/src/assets/icons/covalent.svg',
            ),
        );
    }



}