import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Author, AuthorService } from './author.service';
import { LoginService } from '../login/login.service';
import { TdDialogService } from '@covalent/core';
import { Book } from '../book/book.service';
import { Theme } from '../theme/theme.service';
import { ThemeColumnComponent } from '../theme/themeColumn.component';
import { Citation } from '../citation/citation.service';
import { TabsService } from '../tabs/tabs.service';
@Component({
    templateUrl: './author.component.html',
})
export class AuthorComponent implements OnInit {

    author: Author;
    books: Book[];
    themes: Theme[];
    citations: Citation[]

    constructor(
        private router: Router,
        public activatedRoute: ActivatedRoute,
        public service: AuthorService,
        public loginService: LoginService,
        private _dialogService: TdDialogService,
        private tabsService: TabsService )
         { this.themes = []; this.citations = []; this.books = [] }

    ngOnInit() {
        this.activatedRoute.params.subscribe(
            params => {
                const id = params['id'];
                var aux: Citation[];
                this.service.getAuthor(id).subscribe(
                    author => {
                    this.author = author
                        this.books = this.author.books
                        this.books.forEach(book => { 
                            //Hay que comprobar que no se repitan temas
                            this.themes.push(book.theme)
                            aux = book.citation
                            aux.forEach(cit => {
                                this.citations.push(cit)
                            })
                        })
                        this.tabsService.addTab(this.author.name, this.router.url)
                    },
                    error => console.error(error)
                );
            }
        )
    }

    saveAuthor(author: Author) {
        console.log(author)
        this.service.saveAuthor(author).subscribe(
            _ => { },
            (error: Error) => console.error('Error updating an author: ' + error),
        );
        window.history.back();
    }

    deleteAuthor(author: Author) {
        this._dialogService.openConfirm({
            message: '¿Quiéres borrar este autor?',
            title: 'Confirmar',
            width: '500px',
            height: '175px'
        }).afterClosed().subscribe((accept: boolean) => {
            if (accept) {
                this.service
                    .deleteAuthor(author)
                    .subscribe((_) => { }, (error) => console.error(error));
            }
            window.history.back();
        });
    }
}
