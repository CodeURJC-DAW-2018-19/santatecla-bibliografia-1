import { Component, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatIconRegistry, MatDialog } from '@angular/material';
import { TdMediaService, TdDigitsPipe, TdLayoutManageListComponent, tdRotateAnimation } from '@covalent/core';
import { DatePipe } from '@angular/common';
import { Author, AuthorService } from './author.service';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../login/login.service';

import { ImagesService } from '../images/images.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { BookService, Book } from '../book/book.service';


@Component({
    selector: 'authorForm',
    templateUrl: './authorForm.component.html',
    styleUrls: ['../app.component.css'],
    animations: [tdRotateAnimation],
})

export class AuthorFormComponent {
    //FOR BUILD PROBLEMS
    maxToDate: Date;
    dateBirth: Date;
    dateDeath: Date;
page: number;
    books:Book[];
    author: Author;
    uploadForm: FormGroup;
    image = null;
    private files: FileList;

    //Docker corrections
    disabled:any;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        public activatedRoute: ActivatedRoute,
        public service: AuthorService,

        private imagesService: ImagesService,
          public serviceBook:BookService,
        public loginService: LoginService) {
        this.author = {
            name: '',
        }
        this.books=[]
        this.page = 0;
        this.uploadForm = this.formBuilder.group({
            profile: ['']
        });
    }


    imageAuthor(image) {
        this.image = image;
    }


        ngOnInit(): void {
        var aux:Book[]; 
            this.serviceBook.getBooks().subscribe(
            books =>{
                aux = books
                aux.forEach(book=>{
                    if(book.author === null){
                        console.log(book)
                        this.books.push(book)
                    }
                })
            } ,
            error => console.log(error)
            );
    
        }

    saveAuthor(author:Author) {
      const formData = new FormData();
        formData.append('file', null);
        author.books=[];
        this.books.forEach(book=>{
            if(book.checked){
                author.books.push(book)
            }
        })
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

        this.service.updateAuthorImage(this.author.id, formData);
    }

    selectEvent(files: File | FileList) {
        if (files instanceof FileList) {
            if (this.files.length > 0) {
                const file = this.files[0];
                this.uploadForm.get('profile').setValue(file);
            }
        }
        else {
            const file = files;
            this.uploadForm.get('profile').setValue(file);
        }
    }
}