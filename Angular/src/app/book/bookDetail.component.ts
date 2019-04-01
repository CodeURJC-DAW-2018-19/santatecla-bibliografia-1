import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Book, BookService } from './book.service';
import { LoginService } from '../login/login.service';

@Component({
  templateUrl: './bookDetail.component.html',
})
export class BookDetailComponent implements OnInit{

    book: Book;

    constructor(
        private router: Router, 
        public activatedRoute: ActivatedRoute, 
        public service: BookService,
        public loginService: LoginService) {}

    ngOnInit() {
        const id = this.activatedRoute.snapshot.params['id'];
        this.service.getBook(id).subscribe(
            book => this.book = book,
            error => console.error(error)
        );
    }
}
