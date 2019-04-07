import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Book, BookService } from './book.service';
import { LoginService } from '../login/login.service';
import { TdDialogService } from '@covalent/core';
import { TabsService } from "../tabs/tabs.service";
import { ImagesService } from "../images/images.service";

@Component({
    selector: 'bookDetail',
    templateUrl: './bookDetail.component.html',

})
export class BookDetailComponent{
    @Input()
    book: Book;
    image = null;

    constructor(
        private router: Router,
        public activatedRoute: ActivatedRoute,
        public service: BookService,
        public loginService: LoginService,
        private _dialogService: TdDialogService,
        private tabsService: TabsService,
        private imagesService: ImagesService) {

    }

    imageBook(image) {
        this.image = image;
    }

    saveBook(book: Book) {
        const formData = new FormData();
        formData.append('file', null);
        this.service.saveBook(book).subscribe(
            book => {
                console.log(book)
                let id = book.id;
                if (this.image !== null) {
                    this.imagesService.imageBook(this.image, id).subscribe(
                        _ => { },
                        (error: Error) => console.error('Error updating an author: ' + error),
                    );
                }
            },
            (error: Error) => console.error('Error updating a book: ' + error),
        ); 
        window.history.back();
    }

    deleteBook(book: Book) {
        this._dialogService.openConfirm({
            message: '¿Quiéres borrar este libro?',
            title: 'Confirmar',
            width: '500px',
            height: '175px'
        }).afterClosed().subscribe((accept: boolean) => {
            if (accept) {
                this.service
                    .deleteBook(book)
                    .subscribe((_) => { }, (error) => console.error(error));
            }
            window.history.back();
        });
    }
}
