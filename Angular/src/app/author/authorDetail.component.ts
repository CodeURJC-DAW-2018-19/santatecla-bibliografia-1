import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Author, AuthorService } from './author.service';
import { LoginService } from '../login/login.service';
import { TdDialogService } from '@covalent/core';
import { TabsService } from "../tabs/tabs.service";
import { ImagesService } from "../images/images.service";
import {environment} from '../../environment/environment';


@Component({
    selector: 'authorDetail',
    templateUrl: './authorDetail.component.html',
})
export class AuthorDetailComponent{
    @Input()
    author: Author;
    aux: String ;


    image = null;

    constructor(
        private router: Router,
        public activatedRoute: ActivatedRoute,
        public service: AuthorService,
        public loginService: LoginService,
        private _dialogService: TdDialogService,
        private tabsService: TabsService,
        private imagesService: ImagesService,
    ) { 
        this.aux = environment.baseRef;
    }

    saveAuthor(author: Author) {
        const formData = new FormData();
        formData.append('file', null);
        this.service.saveAuthor(author).subscribe(
            author => {
                console.log(author)
                let id = author.id;
                if (this.image !== null) {
                    this.imagesService.imageAuthor(this.image, id).subscribe(
                        _ => { },
                        (error: Error) => console.error('Error updating an author: ' + error),
                    );
                }
            },
            (error: Error) => console.error('Error updating an author: ' + error),
        );
        window.history.back();
    }

    imageAuthor(image) {
        this.image = image;
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
