import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Author, AuthorService } from './author.service';
import { LoginService } from '../login/login.service';

@Component({
  templateUrl: './authorDetail.component.html',
})
export class AuthorDetailComponent implements OnInit{

    author: Author;

    constructor(
        private router: Router, 
        public activatedRoute: ActivatedRoute, 
        public service: AuthorService,
        public loginService: LoginService) {}

    ngOnInit() {
        const id = this.activatedRoute.snapshot.params['id'];
        this.service.getAuthor(id).subscribe(
            author => this.author = author,
            error => console.error(error)
        );
    }

    saveEditAuthor(author:Author) {
        console.log(author)
        this.service.patchAuthor(author).subscribe(
            _ => {},
            (error: Error) => console.error('Error updating an author: ' + error),
        );
        window.history.back();
    } 
}
