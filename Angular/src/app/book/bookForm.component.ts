import { Component, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { MatIconRegistry, MatDialog } from '@angular/material';
import { TdMediaService, TdDigitsPipe, TdLayoutManageListComponent, tdRotateAnimation } from '@covalent/core';
import {ActivatedRoute,Router} from '@angular/router'
import { Book, BookService } from './book.service';
import { LoginService } from '../login/login.service';
import { Author, AuthorService } from '../author/author.service';

@Component({
    selector: 'bookForm',
    templateUrl: './bookForm.component.html',
    styleUrls: ['../app.component.css'],
    animations: [tdRotateAnimation],
})
export class BookFormComponent {
   
    //FOR BUILD PROBLEMS
    maxToDate: Date;
    
    book:Book;

    authors:Author[];

    constructor(
        private router: Router,
        activatedRoute: ActivatedRoute,
        public service: BookService,
        public serviceAuthor: AuthorService,
        public loginService: LoginService
    ){            
        this.book={
            title:'',
        }
    }

    ngOnInit(): void {
        this.serviceAuthor.getAuthors().subscribe(
        authors =>{this.authors=authors},
        error => console.log(error)
        );
    
    }
// Timeframe
date: Date = new Date(new Date().getTime() - 2 * 60 * 60 * 24 * 1000);

saveBook(book:Book) {
    /*this.authors.forEach(author=>{
        if(author.checked){
            book.author=author;
            console.log(book.author)
        }
    })*/
    console.log(book.author)
    this.service.saveBook(book).subscribe(
        _ => {},
        (error: Error) => console.error('Error updating a book: ' + error),
    ); 
    window.history.back();
}

gotoBooks() {
    this.router.navigate(['/books']);
}
}