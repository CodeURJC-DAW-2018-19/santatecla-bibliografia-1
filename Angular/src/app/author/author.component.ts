import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Author, AuthorService } from './author.service';
import { LoginService } from '../login/login.service';
import { TdDialogService } from '@covalent/core';
import { Book } from '../book/book.service';
import { Theme } from '../theme/theme.service';
import { ThemeColumnComponent } from '../theme/themeColumn.component';

@Component({
  templateUrl: './author.component.html',
})
export class AuthorComponent implements OnInit{

    author: Author;
    books:Book[];
    themes: Theme[];

    constructor(
        private router: Router, 
        public activatedRoute: ActivatedRoute, 
        public service: AuthorService,
        public loginService: LoginService,
        private _dialogService: TdDialogService,) {}

    ngOnInit() {
        const id = this.activatedRoute.snapshot.params['id'];
        this.service.getAuthor(id).subscribe(
            author => {this.author = author
            this.books=this.author.books
            /*for(var item in this.books){
                this.themes.push();                
            }*/
            console.log(this.author)
        },
            error => console.error(error)
        );
        console.log("Current location:", this.router.url);
    }

    saveAuthor(author:Author) {
        console.log(author)
        this.service.saveAuthor(author).subscribe(
            _ => {},
            (error: Error) => console.error('Error updating an author: ' + error),
        ); 
        window.history.back();
    }
    
    deleteAuthor(author:Author) {
        this._dialogService.openConfirm({
            message: '¿Quiéres borrar este autor?',
            title: 'Confirmar', 
            width: '500px', 
            height: '175px'
        }).afterClosed().subscribe((accept: boolean) => {
            if (accept) {
                this.service
                    .deleteAuthor(author)
                    .subscribe((_) => {}, (error) => console.error(error));
            }
            window.history.back();
        });
    }
}
