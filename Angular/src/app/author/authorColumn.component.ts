import { Component, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MatIconRegistry, MatDialog } from '@angular/material';
import { TdMediaService, TdDigitsPipe, TdLayoutManageListComponent, tdRotateAnimation } from '@covalent/core';
import {ActivatedRoute,Router} from '@angular/router'
import { Author, AuthorService } from './author.service';

@Component({
    selector: 'authorColumn',
    templateUrl: './authorColumn.component.html',
    styleUrls: ['../app.component.css'],
    animations: [tdRotateAnimation],
})
export class AuthorColumnComponent {
   
    constructor(
        private router: Router,
        activatedRoute: ActivatedRoute,
    ){}
    
    newAuthor() {
        this.router.navigate(['/author']);
    }




}