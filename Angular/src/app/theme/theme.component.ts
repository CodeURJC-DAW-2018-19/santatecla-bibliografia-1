import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Author, AuthorService } from '../author/author.service';
import { LoginService } from '../login/login.service';
import { TdDialogService } from '@covalent/core';
import { Book } from '../book/book.service';
import { Theme, ThemeService } from './theme.service';
import { ThemeColumnComponent } from './themeColumn.component';
import { Citation } from '../citation/citation.service';
import { TabsService } from '../tabs/tabs.service';
@Component({
    templateUrl: './theme.component.html', 
})
export class ThemeComponent implements OnInit {
    theme: Theme;
    authors: Author[];
    books: Book[];
    citations: Citation[]

    constructor(
        private router: Router,
        public activatedRoute: ActivatedRoute,
        public service: ThemeService,
        public loginService: LoginService,
        private _dialogService: TdDialogService,
        private tabsService: TabsService )
         { this.authors = []; this.citations = []; this.books = [] }

    ngOnInit() {
        this.activatedRoute.params.subscribe(
            params => {
                const id = params['id'];
                var aux: Citation[];
                this.service.getTheme(id).subscribe(
                    theme => {
                    this.theme = theme
                        this.books = this.theme.books
                        this.books.forEach(book => { 
                            //Hay que comprobar que no se repitan autores
                            this.authors.push(book.author)
                            aux = book.citation
                            aux.forEach(cit => {
                                this.citations.push(cit)
                            })
                        })
                        this.tabsService.addTab(this.theme.name, this.router.url)
                    },
                    error => console.error(error)
                );
            }
        )
    }

    saveTheme(theme:Theme) {
        console.log(theme)
        this.service.saveTheme(theme).subscribe( 
            _ => { },
            (error: Error) => console.error('Error updating an author: ' + error),
        );
        window.history.back();
    }

    deleteTheme(theme: Theme) {
        this._dialogService.openConfirm({
            message: '¿Quiéres borrar este tema?',
            title: 'Confirmar',
            width: '500px',
            height: '175px'
        }).afterClosed().subscribe((accept: boolean) => {
            if (accept) {
                this.service
                    .deleteTheme(theme)
                    .subscribe((_) => { }, (error) => console.error(error));
            }
            window.history.back();
        });
    }
}
