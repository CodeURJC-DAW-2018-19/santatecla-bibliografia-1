import { Component, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { MatIconRegistry, MatDialog } from '@angular/material';
import { TdMediaService, TdDigitsPipe, TdLayoutManageListComponent, tdRotateAnimation } from '@covalent/core';
import { DatePipe } from '@angular/common';
import { single, multi, pie, times } from '../histogram/data';
import { Author, AuthorService } from './author.service';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../login/login.service';
import { BookService, Book } from '../book/book.service';

@Component({
    selector: 'authorForm',
    templateUrl: './authorForm.component.html',
    styleUrls: ['../app.component.css'],
    animations: [tdRotateAnimation],
})
export class AuthorFormComponent implements OnInit{
    page: number;
    
    books:Book[];
    author: Author;

    constructor(
        private router: Router, 
        public activatedRoute: ActivatedRoute, 
        public service: AuthorService,
        public serviceBook:BookService,
        public loginService: LoginService) {
            this.author={
                name:'',
            }
            this.books=[]
            this.page = 0;
        }

        ngOnInit(): void {
        var createUrl: string;
        createUrl = "?page=" + (this.page);
        console.log(createUrl);
        var aux:Book[];
            this.serviceBook.getBooks(createUrl).subscribe(
            books =>{
                aux = books
                aux.forEach(book=>{
                    if(book.author == null){
                        console.log(book)
                        this.books.push(book)
                    }
                    else{
                        
                    }
                })
            } ,
            error => console.log(error)
            );
    
        }

    saveAuthor(author:Author) {
        console.log(author)
        this.service.saveAuthor(author).subscribe(
            _ => {},
            (error: Error) => console.error('Error updating an author: ' + error),
        ); 
        window.history.back();
    }

}