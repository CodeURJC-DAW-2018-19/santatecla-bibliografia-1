import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Author, AuthorService } from './author.service';
import { LoginService } from '../login/login.service';

@Component({
    template: `
  <div *ngIf="author">
  <h2>Author "{{author.name}}"</h2>
  <div>
    <p>fdsfs</p>
  </div>
  <p>
    <br>
  </p>
  </div>`
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
}
