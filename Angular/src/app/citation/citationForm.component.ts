import { Component } from '@angular/core';
import { tdRotateAnimation } from '@covalent/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../login/login.service';
import { Citation, CitationService } from './citation.service';
import { Book, BookService } from '../book/book.service';
import { Theme, ThemeService } from '../theme/theme.service';


@Component({
    selector: 'authorForm',
    templateUrl: './citationForm.component.html',
    styleUrls: ['../app.component.css'],
    animations: [tdRotateAnimation],
})
export class CitationFormComponent {

    citation: Citation;
    auxBook: string;
    auxTheme: string;

    tmpBook: Book;
    tmpTheme: Theme;

    constructor(
        private router: Router, 
        public activatedRoute: ActivatedRoute, 
        public service: CitationService,
        public themeService: ThemeService,
        public bookService: BookService,
        public loginService: LoginService) {
            this.citation={
                text:'',
                book: null,
                theme: null,
            }
            
        }

        

    formCitation(citation:Citation,auxBook:string,auxTheme:string){
        var bk: Book;
        this.citation.text = citation.text
        this.bookService.getBook(auxBook).subscribe(
            book=> bk = book
        )
        console.log("Sigues vacio?")
        console.log(bk)
        
        this.themeService.getThemeSt(auxTheme).subscribe(
            th => citation.theme = th,
        )
        console.log("Esto es lo que sale")
        console.log(citation.text)
        console.log(citation.theme)
        console.log(citation.book)
        console.log(citation)
        
        console.log("Salio?")
        
        this.saveCitation(citation);
    }

    saveCitation(citation:Citation) {
        console.log("Empezamos a hacer cosas"+citation);
        console.log(citation);
        this.service.saveCitation(citation).subscribe(
            _ => {},
            (error: Error) => console.error('Error updating an author: ' + error),
        ); 
        window.history.back();
    }

}