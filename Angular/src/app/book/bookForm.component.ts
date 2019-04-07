import { Component, OnInit, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { MatIconRegistry, MatDialog } from '@angular/material';
import { TdMediaService, TdDigitsPipe, TdLayoutManageListComponent, tdRotateAnimation } from '@covalent/core';
import {ActivatedRoute,Router} from '@angular/router'
import { Book, BookService } from './book.service';
import { LoginService } from '../login/login.service';

import { ImagesService } from '../images/images.service';
import { Author, AuthorService } from '../author/author.service';
import { ThemeService, Theme } from '../theme/theme.service';


@Component({
    selector: 'bookForm',
    templateUrl: './bookForm.component.html',
    styleUrls: ['../app.component.css'],
    animations: [tdRotateAnimation],
})
export class BookFormComponent implements OnInit{
   
    //FOR BUILD PROBLEMS
    maxToDate: Date;
    disabled:any;
    
    book:Book;
    image = null;

    authors:Author[];

    themes:Theme[];

    constructor(
        private router: Router,
        activatedRoute: ActivatedRoute,
        public service: BookService,

        private imagesService: ImagesService,
        public serviceAuthor: AuthorService,
        public serviceTheme: ThemeService,
        public loginService: LoginService
    ){            
        this.book={
            title:'',
        }
    }

    imageBook(image) {
        this.image = image;
    }

    // Timeframe
    date: Date = new Date(new Date().getTime() - 2 * 60 * 60 * 24 * 1000);

    saveBook(book:Book) {
        const formData = new FormData();
        formData.append('file', null);
        this.service.saveBook(book).subscribe(
            book => {
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

    ngOnInit(): void {
        this.serviceAuthor.getAuthors().subscribe(
        authors =>{this.authors=authors},
        error => console.log(error)
        );
        this.serviceTheme.getThemes().subscribe(
            themes =>{this.themes=themes},
            error => console.log(error)
            );
    
    }


gotoBooks() {
    this.router.navigate(['/books']);
}
}