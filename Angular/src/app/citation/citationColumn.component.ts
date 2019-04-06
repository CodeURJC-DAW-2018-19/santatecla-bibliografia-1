
import { Component, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef, OnInit, Input } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MatIconRegistry, MatDialog } from '@angular/material';
import { TdMediaService, TdDigitsPipe, TdLayoutManageListComponent, tdRotateAnimation } from '@covalent/core';
import {ActivatedRoute,Router} from '@angular/router'

import { LoginService } from '../login/login.service';
import { Citation, CitationService } from './citation.service';

@Component({
    selector: 'citationColumn',
    templateUrl: './citationColumn.component.html',
    styleUrls: ['../app.component.css'],
    animations: [tdRotateAnimation],
})
export class CitationColumnComponent implements OnInit{
    @Input()
    citations: Citation[];
    page: number;
    text: string;

    constructor(
        private router: Router,
        activatedRoute: ActivatedRoute,
        private service: CitationService,
        public loginService: LoginService
    ){
        this.page=0;
    }

    ngOnInit() {
        var createUrl: string;
        createUrl = "?page=" + (this.page);
        console.log(createUrl);
        if(this.citations!=null){}
        else{
            this.service.getCitations(createUrl).subscribe(
            citations => this.citations = citations,
            error => console.log(error)
            );
        }
    }

    searchCitation(text:string){
        console.log("searchpulsado")
        console.log("Citation search text: ", text)
        this.service.searchCitation(text).subscribe(            
            citations => this.citations = citations,
            error => console.log(error) 
        );
        console.log(this.citations);
    }

    deleteCitation(citation:Citation){
        console.log("delete pulsado con id: ", citation.id)
        let aux:Citation[];
        aux = this.citations;
        aux.forEach( (item, index) => {
            if(item === citation) aux.splice(index,1);
          });
        this.service.deleteCitation(citation).subscribe(            
            citations =>  this.citations = aux,
            error => console.log(error) 
        );
        console.log(this.citations);
    }

    loadMore(){
        var createUrl: string;
        this.page +=1;
        createUrl = "?page=" + (this.page);
        this.service.getCitations(createUrl).subscribe(
            citations => this.citations = this.citations.concat(citations),
            error => console.log(error)
         );
         console.log(this.citations);
    }
    
    newCitation() {
        this.router.navigate(['/citation']);
    }


}
