import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Author, AuthorService } from './author.service';
import { LoginService } from '../login/login.service';
import { TdDialogService } from '@covalent/core';

@Component({
    selector: 'authorDetail',
    templateUrl: './authorDetail.component.html',
})
export class AuthorDetailComponent implements OnInit{
    @Input()
    author: Author;

    constructor(
        private router: Router, 
        public activatedRoute: ActivatedRoute, 
        public service: AuthorService,
        public loginService: LoginService,
        private _dialogService: TdDialogService,) {}

    ngOnInit() {
        
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
