import { Component, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MatIconRegistry, MatDialog } from '@angular/material';
import { TdMediaService, TdDigitsPipe, TdLayoutManageListComponent, tdRotateAnimation } from '@covalent/core';
import {ActivatedRoute,Router} from '@angular/router'
import { Book, BookService } from './book.service';
import {environment} from '../../environment/environment';


@Component({
    selector: 'bookColumn',
    templateUrl: './bookColumn.component.html',
    styleUrls: ['../app.component.css'],
    animations: [tdRotateAnimation],
})
export class BookColumnComponent implements OnInit{
    
    books: Book[];
    aux: String;
    page: number;
    constructor(
        private router: Router,
        activatedRoute: ActivatedRoute,
        private service: BookService,
    ){
        this.page = 0;
        this.aux = environment.baseRef;
    }

    ngOnInit() {
        var createUrl: string;
        createUrl = "?page=" + (this.page);
        console.log(createUrl);
        this.service.getBooks(createUrl).subscribe(
          books => this.books = books,
          error => console.log(error)
        );
        console.log(this.books)
    } 

    loadMore(){
        var createUrl: string;
        this.page +=1;
        createUrl = "?page=" + (this.page);
        this.service.getBooks(createUrl).subscribe(
            books => this.books = this.books.concat(books),
            error => console.log(error)
         );
         console.log(this.books);
    }

    newBook(){
        this.router.navigate(['/book']);
    }
}