import { Component, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef, OnInit, Input } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MatIconRegistry, MatDialog } from '@angular/material';
import { TdMediaService, TdDigitsPipe, TdLayoutManageListComponent, tdRotateAnimation } from '@covalent/core';
import {ActivatedRoute,Router} from '@angular/router'
import { Author, AuthorService } from './author.service';
import { LoginService } from '../login/login.service';

@Component({
    selector: 'authorColumn',
    templateUrl: './authorColumn.component.html',
    styleUrls: ['../app.component.css'],
    animations: [tdRotateAnimation],
})
export class AuthorColumnComponent implements OnInit{
   @Input()
    authors: Author[];
    page: number;
    name:string; 

    constructor(
        private router: Router,
        activatedRoute: ActivatedRoute,
        private service: AuthorService,
        public loginService: LoginService
    ){
        this.page = 0;
    }

    ngOnInit() {
        var createUrl: string;
        createUrl = "?page=" + (this.page);
        console.log(createUrl);
        if (this.authors!=null){
        }
        else{
            this.service.getAuthors(createUrl).subscribe(
            authors => this.authors = authors,
            error => console.log(error)
            );
        }
    }

    searchAuthor(name:string){
        console.log("searchpulsado")
        console.log("Author search name: ", name)
        this.service.searchAuthor(name).subscribe(            
            authors => this.authors = authors,
            error => console.log(error) 
        );
        console.log(this.authors);
    }

    deleteAuthor(author:Author){
        console.log("delete pulsado con id: ", author.id)
        let aux:Author[];
        aux = this.authors;
        aux.forEach( (item, index) => {
            if(item === author) aux.splice(index,1);
          });
        this.service.deleteAuthor(author).subscribe(            
            authors =>  this.authors = aux,
            error => console.log(error) 
        );
        console.log(this.authors);
    }

    loadMore(){
        var createUrl: string;
        this.page +=1;
        createUrl = "?page=" + (this.page);
        this.service.getAuthors(createUrl).subscribe(
            authors => this.authors = this.authors.concat(authors),
            error => console.log(error)
         );
        console.log(this.authors);
    }
    
    newAuthor() {
        this.router.navigate(['/author']);
    }




}