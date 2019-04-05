import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';


import { LoginService } from '../login/login.service';
import { TdDialogService } from '@covalent/core';
import { Book, BookService } from './book.service';
import { Theme} from '../theme/theme.service';
import { ThemeColumnComponent } from '../theme/themeColumn.component';
import { Author } from '../author/author.service';

@Component({
  templateUrl: './book.component.html', 
})
export class BookComponent implements OnInit{

    book: Book;
    authors:Author[];
    themes: Theme[];

    constructor(
        private router: Router, 
        public activatedRoute: ActivatedRoute, 
        public service: BookService,
        public loginService: LoginService,
        private _dialogService: TdDialogService,) {this.themes=[];
        this.authors=[];
        }

    ngOnInit() {
        const id = this.activatedRoute.snapshot.params['id'];
        this.service.getBook(id).subscribe(
            book => {this.book = book
            this.authors.push(book.author)
            this.themes.push(book.theme)
            
            console.log(this.book)
        },
            error => console.error(error)
        );
        console.log("Current location:", this.router.url);
    }

}
