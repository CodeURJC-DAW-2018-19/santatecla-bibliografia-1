import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Book, BookService } from './book.service';
import { LoginService } from '../login/login.service';
import { TdDialogService } from '@covalent/core';

@Component({
    selector: 'bookDetail',
    templateUrl: './bookDetail.component.html',

})
export class BookDetailComponent implements OnInit{
    @Input()
    book: Book;
    

    constructor(
        private router: Router, 
        public activatedRoute: ActivatedRoute, 
        public service: BookService,
        public loginService: LoginService,
        private _dialogService: TdDialogService) {

        }

    ngOnInit() {
        
        console.log("Current location:", this.router.url);
    }

    saveBook(book:Book) {
        console.log(book)
        this.service.saveBook(book).subscribe(
            _ => {},
            (error: Error) => console.error('Error updating a book: ' + error),
        ); 
        window.history.back();
    }
    
    deleteBook(book:Book) {
        this._dialogService.openConfirm({
            message: '¿Quiéres borrar este libro?',
            title: 'Confirmar', 
            width: '500px', 
            height: '175px'
        }).afterClosed().subscribe((accept: boolean) => {
            if (accept) {
                this.service
                    .deleteBook(book)
                    .subscribe((_) => {}, (error) => console.error(error));
            }
            window.history.back();
        });
    }
}
