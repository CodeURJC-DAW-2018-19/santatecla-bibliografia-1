import { Component, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef, OnInit } from '@angular/core';
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
export class AuthorColumnComponent implements OnInit{
   
    authors: Author[];

    constructor(
        private router: Router,
        activatedRoute: ActivatedRoute,
        private service: AuthorService,
    ){}

    ngOnInit() {
        this.service.getAuthors().subscribe(
           authors => this.authors = authors,
           error => console.log(error)
        );
    }
    
    newAuthor() {
        this.router.navigate(['/author']);
    }




}