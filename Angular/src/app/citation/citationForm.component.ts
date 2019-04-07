import { Component } from '@angular/core';
import { tdRotateAnimation } from '@covalent/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../login/login.service';
import { Citation, CitationService } from './citation.service';
import { Book, BookService } from '../book/book.service';
import { Theme, ThemeService } from '../theme/theme.service';


@Component({
    selector: 'citationForm',
    templateUrl: './citationForm.component.html',
    styleUrls: ['../app.component.css'],
})
export class CitationFormComponent {

    citation: Citation;
    books:Book[];
    themes: Theme[];
  
    constructor(
        private router: Router, 
        public activatedRoute: ActivatedRoute, 
        public service: CitationService,
        public themeService: ThemeService,
        public bookService: BookService,
        public loginService: LoginService) 
        {
            this.citation={
                text:'',
                book: null,
                theme: null,
            }
            
        }

    ngOnInit(): void {
        this.themeService.getThemes().subscribe(
            th => {this.themes = th},
            error => console.log(error)
            );
        this.bookService.getBooks().subscribe(
            book =>{this.books=book},
            error => console.log(error)
            );
    }
    
    saveCitation(citation:Citation) {
        console.log(citation);
        this.service.saveCitation(citation).subscribe(
            _ => {},
            (error: Error) => console.error('Error updating an author: ' + error),
        ); 
        window.history.back();
    }

}