import { Component, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MatIconRegistry, MatDialog } from '@angular/material';
import { TdMediaService, TdDigitsPipe, TdLayoutManageListComponent, tdRotateAnimation } from '@covalent/core';
import {ActivatedRoute,Router} from '@angular/router'
import { Book, BookService } from './book.service';

@Component({
    selector: 'bookColumn',
    templateUrl: './bookColumn.component.html',
    styleUrls: ['../app.component.css'],
    animations: [tdRotateAnimation],
})
export class BookColumnComponent implements OnInit{
    
    books: Book[];

    constructor(
        private router: Router,
        activatedRoute: ActivatedRoute,
        private service: BookService,
    ){}

    ngOnInit() {
        this.service.getBooks().subscribe(
          books => this.books = books,
          error => console.log(error)
        );
    } 

    newBook(){
        this.router.navigate(['/book']);
    }
}