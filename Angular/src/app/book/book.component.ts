import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { TdDialogService } from '@covalent/core';
import { Book, BookService } from './book.service';
import { Theme } from '../theme/theme.service';
import { ThemeColumnComponent } from '../theme/themeColumn.component';
import { Author } from '../author/author.service';
import { Citation } from '../citation/citation.service';
import { TabsService } from '../tabs/tabs.service';

@Component({
    templateUrl: './book.component.html',
})
export class BookComponent implements OnInit {

    book: Book;
    authors: Author[];
    themes: Theme[];
    citations: Citation[];

    constructor(
        private router: Router,
        public activatedRoute: ActivatedRoute,
        public service: BookService,
        public loginService: LoginService,
        private _dialogService: TdDialogService,
        private tabsService: TabsService ) {
        this.themes = [];
        this.authors = []; this.citations = []
    }

    ngOnInit() {
        this.activatedRoute.params.subscribe(
            params => {
                const id = params['id'];
                var aux: Citation[];
                this.service.getBook(id).subscribe(
                    book => {
                    this.book = book
                        this.authors.push(book.author)
                        this.themes.push(book.theme)
                        aux = book.citation;
                        aux.forEach(cit => {
                            this.citations.push(cit)
                        });
                        this.tabsService.addTab(this.book.title, this.router.url)
                        console.log(this.book)
                    },
                    error => console.error(error)
                );
            }
        )
    }

}
